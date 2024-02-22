package com.group1.studentsocialbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group1.studentsocialbackend.DTO.PostDTO;
import com.group1.studentsocialbackend.PO.Comment;
import com.group1.studentsocialbackend.mapper.CommentMapper;
import com.group1.studentsocialbackend.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group1.studentsocialbackend.PO.Post;

import com.group1.studentsocialbackend.PO.User;
import com.group1.studentsocialbackend.mapper.PostMapper;
import com.group1.studentsocialbackend.util.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    public Post createOne(Post post) {
        post.setCreatorId(SessionContext.getUserId());
        post.setLikes(0);
        //post.setImageUrl("");
        postMapper.insert(post);
        return post;
    }

    public Post likePost(String postId) {
        Post post=postMapper.selectOne(new QueryWrapper<Post>().eq("id", postId));
        post.setLikes(post.getLikes()+1);
        postMapper.updateById(post);
        return post;
    }

    public List<Post> getListOfUserPosts(String id) {
        return postMapper.selectList(new QueryWrapper<Post>().eq("creator_id", id));
    }

    public List<PostDTO> getListOfPostsByTimeline(Integer currentPage) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time", "create_time");
        Page<Post> pages = new Page<>(currentPage, 10);
        IPage<Post> iPage = postMapper.selectPage(pages, queryWrapper);
        //System.out.println("参数: " + currentPage);
        System.out.println("Total Pages: " + iPage.getPages());
        System.out.println("Total Records: " + iPage.getTotal());
        System.out.println("Current Page: " + iPage.getCurrent());
        List<Post> postList = iPage.getRecords();

        //DTO data type with username
        List<PostDTO> postVOlist= new ArrayList<>();
        for(Post p: postList)
        {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setContent(p.getContent());
            postDTO.setLikes(p.getLikes());
            postDTO.setCreatorId(p.getCreatorId());
            postDTO.setCreateTime(p.getCreateTime());
            postDTO.setUpdateTime(p.getUpdateTime());
            postDTO.setIsAnonymous(p.getIsAnonymous());
            postDTO.setImageUrl(p.getImageUrl());
            String username=userMapper.selectOne(new QueryWrapper<User>().eq("id", p.getCreatorId())).getUsername();
            postDTO.setUsername(username);
            postVOlist.add(postDTO);
            System.out.println(postDTO);
        }
        //postList.forEach(System.out::println);
        return postVOlist;
    }
    public List<Comment> getListOfComments(String id) {
        return commentMapper.selectList(new QueryWrapper<Comment>().eq("post_id", id));
    }
}
