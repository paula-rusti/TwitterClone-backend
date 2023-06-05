package com.example.TwitterClone.models.orm;

import com.example.TwitterClone.models.orm.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

// only like for the moment - later add an enum to specify the reaction type
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference(value = "postReactions")
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

//    @JoinColumn(name = "user_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    ApplicationUser user;

    @JsonBackReference(value = "User reactions")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable=false, updatable=false)
    private ApplicationUser user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
