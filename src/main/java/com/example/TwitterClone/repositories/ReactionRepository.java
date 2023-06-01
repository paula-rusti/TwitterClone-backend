package com.example.TwitterClone.repositories;

import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.models.orm.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    Optional<Reaction> findById(Long id);

}
