package com.group1.studentsocialbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group1.studentsocialbackend.PO.Post;

import com.group1.studentsocialbackend.mapper.PostMapper;
import com.group1.studentsocialbackend.util.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public List<Post> getListOfUserPosts(String id) {
        return postMapper.selectList(new QueryWrapper<Post>().eq("creator_id", id));
    }

}
