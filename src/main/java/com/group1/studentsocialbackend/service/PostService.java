package com.group1.studentsocialbackend.service;

import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public Post createOne(Post post) {
        postMapper.insert(post);
        return post;
    }

    public List<Post> getList() {
        return postMapper.selectList(null);
    }
}
