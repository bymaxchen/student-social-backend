package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.service.PostService;
import com.group1.studentsocialbackend.util.SessionContext;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Resource
    private PostService postService;
    // Create a new post
    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        return postService.createOne(post);
    }

    // Get all posts
    @GetMapping("/userposts")
    public List<Post> getAllPostsOfCurrentUser() {
        return postService.getListOfUserPosts(SessionContext.getUserId());
    }
    @GetMapping("/homeposts")
    public List<Post> getPostsForHomePage(@RequestParam("page") Integer page) {
        return postService.getListOfPostsByTimeline(page);
    }
    // Get a single post by id
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//
//    }
//
//    // Update a post
//    @PutMapping("/{id}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
//
//    }
//
//    // Delete a post
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable Long id) {
//
//    }
}