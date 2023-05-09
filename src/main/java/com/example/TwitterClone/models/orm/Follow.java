package com.example.TwitterClone.models.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follow")
public class Follow {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "followed_id")
    private Long followedId;

    @Column(name = "follower_id")
    private Long followerId;

    @JsonBackReference(value = "User followers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id_join", referencedColumnName = "user_id")
    private ApplicationUser followerUser;

    @JsonBackReference(value = "Followed users")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id_join", referencedColumnName = "user_id")
    private ApplicationUser followedUser;

    public Follow(Long followedId, Long followerId) {
        this.followedId = followedId;
        this.followerId = followerId;
    }
}