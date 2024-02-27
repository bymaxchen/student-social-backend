package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Message")
public class Message {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    private String content;
    private String senderId;
    private String receiverId;
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
