package com.example.TwitterClone.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.TwitterClone.constants.ErrorMessages.*;

@Data
public class RegistrationRequest {

    @Email(regexp = ".+@.+\\..+", message = EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = BLANK_USERNAME)
    @Size(min = 1, max = 50, message = USERNAME_NOT_VALID)
    private String username;

    private String firstName;
    private String lastName;

    @NotBlank(message = BLANK_PASSWORD)
    private String password;

}
