package com.example.TwitterClone.services;

import com.example.TwitterClone.mappers.PostMapper;
import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.request.CreateReactionRequest;
import com.example.TwitterClone.models.dto.request.RepostRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.orm.Follow;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.models.orm.Reaction;
import com.example.TwitterClone.repositories.FollowRepository;
import com.example.TwitterClone.repositories.PostRepository;
import com.example.TwitterClone.repositories.ReactionRepository;
import com.example.TwitterClone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ReactionRepository reactionRepository;
    private final FollowRepository followRepository;

    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, ReactionRepository reactionRepository, PostMapper postMapper, FollowRepository followRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.reactionRepository = reactionRepository;
        this.followRepository = followRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    private String extractMentions(String content) {
        String regex = "@([a-zA-Z0-9_]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        List<String> mentions = new ArrayList<>();

        while (matcher.find()) {
            String username = matcher.group(1);
            mentions.add(username);
        }
        return String.join(",", mentions);
    }

    public Post addPost(CreatePostRequest createPostRequest) {
        String mentions = extractMentions(createPostRequest.getContent());
        createPostRequest.setMentions(mentions);
        return postRepository.save(postMapper.postRequestToEntity(createPostRequest));
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public List<PostResponse> getUserPosts(Long userId) {
        // retrieve all posts owned by the given user
        List<Post> retrievedPosts = postRepository.findAllByOwner_UserId(userId);
        return retrievedPosts.stream().map(postMapper::entityToPostResponse).toList();
    }

    public List<PostResponse> getUserPosts(String username, LocalDateTime date) {
        // retrieve all posts owned by the given user and newer than the given date
        Long userId = Long.valueOf(userRepository.findByUsername(username).get().getUserId());
        List<Post> retrievedPosts = postRepository.findPostsNewerThan(Math.toIntExact(userId), date);
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
            retrievedPosts.addAll(postRepository.findAllByOwner_UserId(followingId));
        }
        return retrievedPosts.stream().map(postMapper::entityToPostResponse).toList();

    }

    public CreateReactionRequest addReaction(CreateReactionRequest createReactionRequest) {
        Reaction addedReaction = reactionRepository.save(postMapper.reactionRequestToEntity(createReactionRequest));
        return postMapper.reactionEntityToReactionRequest(addedReaction);
    }

    public PostResponse deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postRepository.deleteById(postId);
            return postMapper.entityToPostResponse(post.get());
        }
        return null;
    }

    @Transactional
    public PostResponse repostPost(RepostRequest repostRequest) {
        Optional<Post> post = postRepository.findById(repostRequest.getPostId());
        if (post.isPresent()) {
            // create a new post belonging to the reposter
            Post repost = new Post(post.get().getContent());
            repost.getOwner().setUserId(Math.toIntExact(repostRequest.getUserId()));
            Post repostedPost = postRepository.save(repost);

            // update the retweets count of the original post
            String reposterUsername = userRepository.findById(Math.toIntExact(repostRequest.getUserId())).get().getUsername();
            postRepository.updateRetweets(repostRequest.getPostId(), reposterUsername);

            return postMapper.entityToPostResponse(repostedPost);
        }
        return null;
    }

    public List<PostResponse> getMentionsPosts(String username) {
        // retrieve all posts that mention the given user
        List<Post> retrievedPosts = postRepository.findPostsByMentionsContaining(username);
        return retrievedPosts.stream().map(postMapper::entityToPostResponse).toList();
    }
}
