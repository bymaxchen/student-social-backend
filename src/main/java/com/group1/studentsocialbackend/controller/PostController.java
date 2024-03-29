package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.DTO.CommentDTO;
import com.group1.studentsocialbackend.PO.Comment;
import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.DTO.PostDTO;
import com.group1.studentsocialbackend.service.PostService;
import com.group1.studentsocialbackend.util.SessionContext;
import jakarta.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.createOne(post), HttpStatus.OK);
    }

    // Get all posts
    @GetMapping("/userposts")
    public ResponseEntity<List<Post>> getAllPostsOfCurrentUser() {
        return new ResponseEntity<>(postService.getListOfUserPosts(SessionContext.getUserId()), HttpStatus.OK);
    }
    @GetMapping("/homeposts")
    public ResponseEntity<List<PostDTO>>  getPostsForHomePage(@RequestParam("page") Integer page) {
        return new ResponseEntity<>(postService.getListOfPostsByTimeline(page), HttpStatus.OK);
    }
    //Get all comment from postId
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getAllPostsOfCurrentUser(@RequestParam("postId") String postId) {
        return new ResponseEntity<>(postService.getListOfComments(postId), HttpStatus.OK);
    }
    // Get a single post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String id) {
        return new ResponseEntity<>(postService.getOne(id), HttpStatus.OK);
    }
//
    // Update a post
    @PutMapping("/likes")
    public ResponseEntity<Post> updatePost(@RequestParam("id") String id) {
        return new ResponseEntity<>(postService.likePost(id), HttpStatus.OK);
    }

    @PutMapping("/unlike")
    public ResponseEntity<Post> unlikePost(@RequestParam("id") String id) {
        return new ResponseEntity<>(postService.unlikePost(id), HttpStatus.OK);
    }
//
//    // Delete a post
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable Long id) {
//
//    }
}