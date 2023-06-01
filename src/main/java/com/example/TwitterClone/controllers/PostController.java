package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.request.CreateReactionRequest;
import com.example.TwitterClone.models.dto.request.CreateReplyRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.dto.response.ReactionResponse;
import com.example.TwitterClone.models.dto.response.ReplyResponse;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Add a post
    @PostMapping
    public ResponseEntity<Post> addPost(@Valid @RequestBody CreatePostRequest request) {
        Post createdPost = postService.addPost(request);
        return ResponseEntity.created(URI.create("/api/v1/posts/" + createdPost.getId())).body(createdPost);
    }

    // Get a post by ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Optional<Post> retrievedPost = postService.getPost(postId);
        return ResponseEntity.ok(retrievedPost.orElse(null));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostResponse>> getUserPosts(@PathVariable Long userId) {
        List<PostResponse> posts = postService.getUserPosts(userId);
        return ResponseEntity.ok(posts);
    }

    // Get a user's feed
    @GetMapping("/users/{userId}/feed")
    public ResponseEntity<List<PostResponse>> getUserFeed(@PathVariable Long userId) {
        List<PostResponse> posts = postService.getUserFeed(userId);
        return ResponseEntity.ok(posts);
    }

    // Add a reaction to a post
    // todo change return type to reaction response
    @PostMapping("/{postId}/reactions")
    public ResponseEntity<CreateReactionRequest> addReaction(@PathVariable Long postId,
                                                        @Valid @RequestBody CreateReactionRequest request) {
        CreateReactionRequest reactionRequest = postService.addReaction(request);
        return ResponseEntity.created(URI.create("/api/v1/posts/" + postId + "/reactions/" + reactionRequest.getPostId()))
                .body(reactionRequest);

    }

    // Add a reply to a post
    @PostMapping("/{postId}/replies")
    public ResponseEntity<ReplyResponse> addReply(@PathVariable Long postId,
                                                  @Valid @RequestBody CreateReplyRequest request) {
//        ReplyDto replyDto = postService.addReply(postId, request);
//        return ResponseEntity.created(URI.create("/api/v1/posts/" + postId + "/replies/" + replyDto.getId()))
//                .body(replyDto));
        System.out.println("not yet impl");
        return null;

    }
}