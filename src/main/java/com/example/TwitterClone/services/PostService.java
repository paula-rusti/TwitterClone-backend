package com.example.TwitterClone.services;

import com.example.TwitterClone.mappers.PostMapper;
import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.orm.Follow;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.repositories.FollowRepository;
import com.example.TwitterClone.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
        this.postMapper = postMapper;
    }

    public Post addPost(CreatePostRequest createPostRequest) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return postRepository.save(new Post(createPostRequest.getContent(), Date.from(Instant.from(now)) ));
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public List<PostResponse> getUserPosts(Long userId) {
        // retrieve all posts owned by the given user
        List<Post> retrievedPosts = postRepository.findAllByOwnerId(userId);
        return retrievedPosts.stream().map(postMapper::entityToPostResponse).toList();

    }

    public List<PostResponse> getUserFeed(Long userId) {
        // retrieve all posts relevant for the given user

        // first find all users that the given user follows
        // then find all posts owned by each of those users
        List<Follow> following = followRepository.findAllByFollowerId(userId);
        List<Long> followingIds = following.stream().map(Follow::getFollowedId).toList();
        List<Post> retrievedPosts = List.of();
        for (Long followingId : followingIds) {
            retrievedPosts.addAll(postRepository.findAllByOwnerId(followingId));
        }
        return retrievedPosts.stream().map(postMapper::entityToPostResponse).toList();

    }
}
