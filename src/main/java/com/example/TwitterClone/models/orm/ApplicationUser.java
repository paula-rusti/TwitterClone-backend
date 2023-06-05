package com.example.TwitterClone.models.orm;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name="users")
public class ApplicationUser {
    // not named User to avoid conflict with Spring security

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @JsonIgnore // we don't hash it for now
    private String password_digest;

    @JsonManagedReference(value = "User posts")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @JsonManagedReference (value = "User followers")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "followerUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> follows;

    @JsonManagedReference (value = "Followed users")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "followedUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> followers;

    @JsonManagedReference (value = "User reactions")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Reaction> reactedPosts;

    @JsonManagedReference (value = "User replies")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Reply> replies;

    public ApplicationUser() {
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password_digest='" + password_digest + '\'' +
                '}';
    }
}
