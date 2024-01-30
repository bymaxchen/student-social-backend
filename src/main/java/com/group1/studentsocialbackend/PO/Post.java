package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Date createTime;
    private Date updateTime;
    private Integer likes;
    private Integer isAnonymous;
}
