package com.group1.studentsocialbackend.websocket;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.group1.studentsocialbackend.PO.BaseResponseMessage;
import com.group1.studentsocialbackend.PO.Message;
import com.group1.studentsocialbackend.PO.UserMessageModel;
import com.group1.studentsocialbackend.config.MyEndpointConfigure;
import com.group1.studentsocialbackend.mapper.MessageMapper;

import com.group1.studentsocialbackend.util.BaseModelEncoder;
import com.group1.studentsocialbackend.util.HashMapEncoder;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
@Component
@ServerEndpoint(value = "/api/websocket/client/{clientId}",encoders = {HashMapEncoder.class, BaseModelEncoder.class},configurator= MyEndpointConfigure.class)
public class WebSocketClient {
    @Autowired
    private  MessageMapper messageMapper;

    public static HashMap<String,WebSocketClient> webSocketClientMap = new HashMap<>();

    public static ConcurrentHashMap<String,List<UserMessageModel>> ToBeSentMap = new ConcurrentHashMap<>();

    public static AtomicInteger onlineUsers = new AtomicInteger();

    private Session infoSession;

    @OnOpen
    public void onOpen(Session session,@PathParam("clientId") String clientId){
        if (!webSocketClientMap.containsKey(clientId)){
            onlineUsers.addAndGet(1);
        }
        webSocketClientMap.put(clientId,this);
        infoSession = session;
        log.info("客户端:{}建立连接，当前在线人数:{}",clientId,onlineUsers.get());

        /**
         * 消息补偿
         */
        if (!CollectionUtils.isEmpty(this.ToBeSentMap.get(clientId))){
            this.ToBeSentMap.get(clientId).forEach(userMessageModel->{
                this.sendMessage(BaseResponseMessage.success(userMessageModel));
            });
        }
    }

    @OnClose
    public void onclose(Session session, @PathParam("clientId") String clientId){
        if (webSocketClientMap.containsKey(clientId)) {
            webSocketClientMap.remove(clientId);
            onlineUsers.getAndAdd(-1);
        }
        log.info("客户端:{}断开连接，当前在线人数:{}",clientId,onlineUsers.get());
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("连接异常:{}",error.getMessage());
    }

    @OnMessage
    public void onMessage(String message, Session session,@PathParam("clientId") String clientId){
        UserMessageModel userMessageModel = JSONObject.parseObject(message, UserMessageModel.class);
        if (userMessageModel == null){
            this.sendMessage(BaseResponseMessage.error(null,"传递参数结构异常"));
        }
        if(!webSocketClientMap.containsKey(userMessageModel.getAcceptId())){
            // 放到待发送列表里
            if(!this.ToBeSentMap.containsKey(userMessageModel.getAcceptId())){
                this.ToBeSentMap.put(userMessageModel.getAcceptId(),new CopyOnWriteArrayList<>());
            }
            List<UserMessageModel> addList = this.ToBeSentMap.get(userMessageModel.getAcceptId());
            addList.add(userMessageModel);
            log.info("客户端:{} 发送消息到接受端:{} 不在线，放置到代发送列表，当前待发送列表:{}条",clientId,userMessageModel.getAcceptId(), addList.size());
            this.sendMessage(BaseResponseMessage.error(null,"接收端不在线"));
        }else{
            log.info("客户端:{} 发送到客户端:{},消息内容:{}",clientId,userMessageModel.getAcceptId(),userMessageModel.getMessage());
            webSocketClientMap.get(userMessageModel.getAcceptId()).sendMessage(BaseResponseMessage.success(userMessageModel));
            this.sendMessage(BaseResponseMessage.success(userMessageModel));
        }
        Message savedMessage=new Message();
        savedMessage.setSenderId(clientId);
        savedMessage.setReceiverId(userMessageModel.getAcceptId());
        savedMessage.setContent(userMessageModel.getMessage());
        messageMapper.insert(savedMessage);
    }

    private void  sendMessage(Object message){
        try {
            this.infoSession.getBasicRemote().sendObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }
}