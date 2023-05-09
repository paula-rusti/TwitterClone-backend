package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.models.dto.request.RegistrationRequest;
import com.example.TwitterClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

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
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody RegistrationRequest user) {
        // Check if username and email are unique
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userService.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        // Save the new user
        ApplicationUser registeredUser = userService.registerUser(new ApplicationUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword())); // tweak constructor
        return ResponseEntity.created(URI.create("/api/v1/users/" + registeredUser.getUserId())).body(registeredUser);
        // todo make custom exceptions when username or email are taken
    }
}
