package com.example.TwitterClone.models.dto.request;

import lombok.Data;

@Data
public class FollowRequest {

    private Long userId;    // the user to be followed
}
