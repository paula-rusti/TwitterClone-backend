package com.example.TwitterClone.repositories;

import com.example.TwitterClone.models.orm.ApplicationUser;
import com.example.TwitterClone.models.orm.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findByFollowedId(Long followedId);
    Optional<Follow> findByFollowerId(Long followerId);

    List<Follow> findAllByFollowerId(Long followerId);
}
