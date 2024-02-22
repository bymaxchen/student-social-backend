package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Comment")
public class Comment {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String postId;
    private String content;
    private String creatorId;
    private String imageUrl;
    private Integer isAnonymous;

    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
