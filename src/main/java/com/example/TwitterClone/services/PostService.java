package com.example.TwitterClone.services;

import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(CreatePostRequest createPostRequest) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return postRepository.save(new Post(createPostRequest.getContent(), Date.from(Instant.from(now)) ));
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }
}
