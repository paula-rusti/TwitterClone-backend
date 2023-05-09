package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.models.dto.request.RegistrationRequest;
import com.example.TwitterClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/")
    public ApplicationUser registerUser(@RequestBody RegistrationRequest user) {
        // Check if username and email are unique
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userService.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        // Save the new user
        return userService.registerUser(new ApplicationUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword())); // tweak constructor
    }
}
