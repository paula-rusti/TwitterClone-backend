package com.example.TwitterClone.models.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @JsonBackReference(value = "post")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_username_join", referencedColumnName = "username")
    private ApplicationUser owner;

    @Column(name = "content", nullable = false)
    private String content;

    // TODO audit column
    @Column(name = "created", nullable = false)
    @CreatedDate
    private Date created;


    @JsonManagedReference(value = "postReactions")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Reaction> postReactions;

    public Post(String content, Date created) {
        this.content = content;
        this.created = created;
    }

    public Post() {

    }
}
