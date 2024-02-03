package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
