package com.group1.studentsocialbackend.DTO;

import com.group1.studentsocialbackend.PO.Comment;
import lombok.Data;

@Data
public class CommentDTO extends Comment {
    private String username;
}
