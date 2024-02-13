package com.group1.studentsocialbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public List<Post> getListOfPostsByTimeline(Integer currentPage) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time", "create_time");
        Page<Post> pages = new Page<>(currentPage, 10);
        IPage<Post> iPage = postMapper.selectPage(pages, queryWrapper);
        //System.out.println("参数: " + currentPage);
        System.out.println("总页数: " + iPage.getPages());
        System.out.println("总个数: " + iPage.getTotal());
        System.out.println("当前页: " + iPage.getCurrent());
        List<Post> postList = iPage.getRecords();
        postList.forEach(System.out::println);
        return postList;
    }

}
