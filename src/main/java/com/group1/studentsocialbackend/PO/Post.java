package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Post")
public class Post {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String title;
    private String content;
    private String creatorId;
    private Integer likes;
    private Integer isAnonymous;

    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
