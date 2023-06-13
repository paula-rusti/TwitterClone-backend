package com.example.TwitterClone.models.dto.request;

import lombok.Data;

@Data
public class RepostRequest {
    private Long postId;
    private Long userId;    // the user who is reposting the post
}
