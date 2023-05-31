package com.example.TwitterClone.models.dto.response;


import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String ownerId;
    private String content;
    private String created;
    // todo reactions
    // todo mentions
}
