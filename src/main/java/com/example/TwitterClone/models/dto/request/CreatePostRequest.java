package com.example.TwitterClone.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.TwitterClone.constants.ErrorMessages.BLANK_CONTENT;

@Data
public class CreatePostRequest {

    @NotBlank(message = BLANK_CONTENT)
    private String content;

    // TODO add mentions here eventually
}
