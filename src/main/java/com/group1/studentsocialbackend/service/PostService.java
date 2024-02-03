package com.group1.studentsocialbackend.service;

import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.mapper.PostMapper;
import com.group1.studentsocialbackend.util.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public Post createOne(Post post) {
        post.setCreatorId(SessionContext.getUserId());
        post.setLikes(0);
        postMapper.insert(post);
        return post;
    }

    public List<Post> getList() {
        return postMapper.selectList(null);
    }

}
