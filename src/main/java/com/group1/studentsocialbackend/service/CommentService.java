package com.group1.studentsocialbackend.service;

import com.group1.studentsocialbackend.PO.Comment;
import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.mapper.PostMapper;
import com.group1.studentsocialbackend.mapper.UserMapper;
import com.group1.studentsocialbackend.mapper.CommentMapper;
import com.group1.studentsocialbackend.util.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    public Comment createOne(String postId, Comment comment) {
        comment.setPostId(postId);
        comment.setCreatorId(SessionContext.getUserId());
        //post.setImageUrl("");
        commentMapper.insert(comment);
        return comment;
    }
}
