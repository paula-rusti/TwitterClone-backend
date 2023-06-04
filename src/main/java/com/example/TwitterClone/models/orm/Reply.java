package com.example.TwitterClone.models.orm;

import jakarta.persistence.*;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;    // the user who is replying

    @Column(name="post_id", nullable = false)
    private Long postId;

    @Column(name="parent_reply_id", nullable = true)
    private Long parentReplyId;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="created", nullable = false)
    private String created;

    @Column(name="is_public", nullable = true)
    private Boolean isPublic;

    // join columns for user, post and eventually parent reply
    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name="post_id", insertable = false, updatable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="parent_reply_id", insertable = false, updatable = false)
    private Reply parentReply;

}
