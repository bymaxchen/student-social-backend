package com.group1.studentsocialbackend.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Posts")
public class Post {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;
    private String content;
    private String author;
}
