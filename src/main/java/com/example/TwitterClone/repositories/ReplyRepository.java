package com.example.TwitterClone.repositories;

import com.example.TwitterClone.models.orm.Reaction;
import com.example.TwitterClone.models.orm.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Optional<Reply> findById(Long id);

}
