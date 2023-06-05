package com.example.TwitterClone.repositories;

import com.example.TwitterClone.models.orm.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Long id);

    List<Post> findAllByOwner_UserId(Long userId);

    Long deleteById(Long id);

    @Query("SELECT p FROM Post p WHERE p.id = :id AND p.created > :date")
    List<Post> findPostsNewerThan(@Param("id") Integer id, @Param("date") LocalDateTime date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE your_entity SET retweets = array_append(retweets, :reposterUsername) WHERE id = :postId", nativeQuery = true)
    void updateRetweets(@Param("postId") Long postId, @Param("reposterUsername") String reposterUsername);

    List<Post> findPostsByMentionsContaining(@Param("username") String username);
}
