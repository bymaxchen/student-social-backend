package com.group1.studentsocialbackend.PO;

import lombok.Data;

@Data
public class BaseResponseMessage<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponseMessage success(T data) {
        BaseResponseMessage baseResponseMessage = new BaseResponseMessage();
        baseResponseMessage.code = "0";
        baseResponseMessage.msg = "成功";
        baseResponseMessage.data = data;
        return baseResponseMessage;
    }

    public static <T> BaseResponseMessage error(T data,String msg) {
        BaseResponseMessage baseResponseMessage = new BaseResponseMessage();
        baseResponseMessage.code = "500";
        baseResponseMessage.msg = msg;
        baseResponseMessage.data = data;
        return baseResponseMessage;
    }
}