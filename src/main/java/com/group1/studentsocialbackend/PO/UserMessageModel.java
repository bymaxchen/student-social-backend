package com.group1.studentsocialbackend.PO;

import lombok.Data;

@Data
public class UserMessageModel {

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送类型：USER
     */
    private String sendType;
    /**
     * 发送端id
     */
    private String sendId;
    /**
     * 接收端id
     */
    private String acceptId;

    /**
     * 接收类型：USER
     */
    private String acceptType;

}