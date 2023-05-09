package com.example.TwitterClone.models.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDetailResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}