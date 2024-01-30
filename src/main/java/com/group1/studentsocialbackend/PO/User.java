package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("User")
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;
    private String password;
    private String email;
    private Date expireTime;
    private boolean isBanned;
    private Date createTime;
    private Date updateTime;
    // Getters and setters

}
