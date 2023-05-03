package com.example.TwitterClone.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "post")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_username", referencedColumnName = "username")
    private ApplicationUser owner;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created", nullable = false)
    @CreatedDate
    private Date created;


    @JsonManagedReference(value = "postReactions")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Reaction> postReactions;
}
