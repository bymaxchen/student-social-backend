package com.group1.studentsocialbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group1.studentsocialbackend.PO.Comment;
import com.group1.studentsocialbackend.PO.Message;
import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.mapper.MessageMapper;
import com.group1.studentsocialbackend.mapper.PostMapper;
import com.group1.studentsocialbackend.mapper.UserMapper;
import com.group1.studentsocialbackend.mapper.CommentMapper;
import com.group1.studentsocialbackend.util.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    public List<Message> chatHistory(String sendId, String acceptId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sender_id", sendId);
        queryWrapper.eq("receiver_id", acceptId);

        return messageMapper.selectList(queryWrapper);
    }
}
