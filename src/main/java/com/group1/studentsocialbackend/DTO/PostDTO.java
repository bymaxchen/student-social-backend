package com.group1.studentsocialbackend.DTO;
import com.group1.studentsocialbackend.PO.Post;
import lombok.Data;

@Data
public class PostDTO extends Post {
    private String username;

}
