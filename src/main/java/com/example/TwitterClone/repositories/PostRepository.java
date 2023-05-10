package com.example.TwitterClone.repositories;

import com.example.TwitterClone.models.orm.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Long id);
}
