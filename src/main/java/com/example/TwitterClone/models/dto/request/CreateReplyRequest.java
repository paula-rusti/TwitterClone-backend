package com.example.TwitterClone.models.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateReplyRequest {
    @NotBlank
    private Long userId;    // the user who is replying

    @NotBlank
    private Long postId;

    @NotBlank
    private Long parentReplyId;

    @NotBlank
    private String content;
}
