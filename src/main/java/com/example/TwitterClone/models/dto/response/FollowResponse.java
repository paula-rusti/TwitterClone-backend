package com.example.TwitterClone.models.dto.response;

import lombok.Data;

@Data
public class FollowResponse {
    private Long id;      // the id of the follow
    private Long followerId;    // the user who follows the other user
    private Long followedId;    // the user to be followed
}
