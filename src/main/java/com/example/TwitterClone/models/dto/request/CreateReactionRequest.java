package com.example.TwitterClone.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReactionRequest {
    @NotBlank
    private Long userId;    // the user who is reacting

    @NotBlank
    private Long postId;

}
