
package com.example.TwitterClone.models.orm;

import com.example.TwitterClone.utils.StringListConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Converter(autoApply = true)
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "owner_id", nullable = false)
//    private Long ownerId;

//    @JsonBackReference(value = "post")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_username_join", referencedColumnName = "username")
//    private ApplicationUser owner;

    @JsonBackReference(value = "User posts")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id", nullable = false)
    private ApplicationUser owner;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private LocalDateTime created;


    @JsonManagedReference(value = "post reactions")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reaction> postReactions;

    @JsonManagedReference(value = "post replies")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reply> postReplies;

    // valid list of existing usernames
    // when the post is added its content is checked for mentions with @username
    // then this field is populated
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "posts", joinColumns = @JoinColumn(name = "post_id"))
//    @Column(name = "mentions", columnDefinition = "text[]", nullable = true)
//    private List<String> mentions;

    @Convert(converter = StringListConverter.class)
    @Column(name = "mentions")
    private List<String> mentions;

    @ElementCollection
    @Column(name = "retweets", nullable = true)
    private List<Long> retweets;

    public Post(String content) {
        this.content = content;
    }

    public Post() {

    }
}
