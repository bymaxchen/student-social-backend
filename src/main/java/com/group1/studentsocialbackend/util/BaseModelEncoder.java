package com.group1.studentsocialbackend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.group1.studentsocialbackend.PO.BaseResponseMessage;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class BaseModelEncoder  implements Encoder.Text<BaseResponseMessage> {
    @Override
    public String encode(BaseResponseMessage baseResponseMessage) throws EncodeException {
        try {
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.writeValueAsString(baseResponseMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
