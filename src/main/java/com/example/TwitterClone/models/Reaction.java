package com.example.TwitterClone.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id")
    @JsonBackReference(value = "postReactions")
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
