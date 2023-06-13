package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.request.CreateReactionRequest;
import com.example.TwitterClone.models.dto.request.CreateReplyRequest;
import com.example.TwitterClone.models.dto.request.RepostRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.dto.response.ReplyResponse;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.services.PostService;
import com.example.TwitterClone.services.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.QueryParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    private final ReplyService replyService;

    @Autowired
    public PostController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    // Add a post
    @PostMapping
    public ResponseEntity<Post> addPost(@Valid @RequestBody CreatePostRequest request) {
        // todo parse post to extract mentions
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

    @GetMapping("/users/{username}/")
    public ResponseEntity<List<PostResponse>> getUserPostsFilter(@PathVariable String username, @RequestParam LocalDateTime timestamp) {
        List<PostResponse> posts = postService.getUserPosts(username, timestamp);
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
        // postId is only used for the URI
        ReplyResponse replyDto = replyService.addReply(request);
        return ResponseEntity.created(URI.create("/api/v1/posts/" + postId + "/replies/" + replyDto.getId())).body(replyDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable Long postId) {
        Optional<PostResponse> deletedPost = Optional.ofNullable(postService.deletePost(postId));
        return ResponseEntity.ok(deletedPost.orElse(null));
    }

    @PostMapping("/{postId}/reposts")
    public ResponseEntity<PostResponse> repost(@PathVariable Long postId, @RequestBody RepostRequest request) {
        PostResponse repost = postService.repostPost(request);
        return ResponseEntity.created(URI.create("/api/v1/posts/" + repost.getId())).body(repost);
    }

    @Operation(summary = "Get posts that mention a given user")
    @GetMapping("mentions/{username}")
    public ResponseEntity<List<PostResponse>> getMentionsPosts(@PathVariable String username) {
        List<PostResponse> posts = postService.getMentionsPosts(username);
        return ResponseEntity.ok(posts);
    }
}