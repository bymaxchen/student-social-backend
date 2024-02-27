package com.group1.studentsocialbackend.util;

import com.alibaba.fastjson2.JSONObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class HashMapEncoder implements Encoder.Text<HashMap> {
    private static final Logger log = LoggerFactory.getLogger(HashMapEncoder.class);

    @Override
    public String encode(HashMap hashMap) throws EncodeException {
        /*
         * 这里是重点，只需要返回Object序列化后的json字符串就行
         * 你也可以使用gosn，fastJson来序列化。
         * 这里我使用fastjson
         */
        try {
            return JSONObject.toJSONString(hashMap);
        }catch (Exception e){
            log.info("ServerEncoder编码异常:{}",e.getMessage());
        }
        return null;
    }
}
