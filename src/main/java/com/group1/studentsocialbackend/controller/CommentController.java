package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.PO.Comment;
import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Resource
    private CommentService commentService;
    // Create a new post
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam("postId") String postId, @RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createOne(postId, comment), HttpStatus.OK);
    }

    // Get all posts
    // Get a single post by id
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//
//    }
//
    // Update a post
//
//    // Delete a post
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable Long id) {
//
//    }
}
