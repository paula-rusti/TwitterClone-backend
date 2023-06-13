package com.example.TwitterClone.models.dto.request;

import lombok.Data;

@Data
public class FollowRequest {

    private Long followerId;    // the user who follows the other user
    private Long followedId;    // the user to be followed
}
