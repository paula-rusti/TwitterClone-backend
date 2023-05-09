package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.orm.ApplicationUser;
import com.example.TwitterClone.models.dto.request.FollowRequest;
import com.example.TwitterClone.models.dto.request.RegistrationRequest;
import com.example.TwitterClone.services.FollowService;
import com.example.TwitterClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    // follow operations are also handled here
    private final UserService userService;
    private final FollowService followService;

    @Autowired
    public UserController(UserService userService, FollowService followService) {
        this.userService = userService;
        this.followService = followService;
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
        // todo add exceptions and controller advice
    }

    // Search for users by username
    @GetMapping("/")
    public ResponseEntity<List<ApplicationUser>> searchUsers(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName) {
        // only one query param active(provided at a time)
        List<ApplicationUser> users = new ArrayList<>();

        if (username != null) {
            Optional<ApplicationUser> user = userService.searchUsersByUsername(username);
            users.add(user.orElse(null));
        }

        if (firstName != null) {
            users = userService.searchUsersByFirstName(firstName).orElse(Collections.emptyList());
        }

        if (lastName != null) {
            users = userService.searchUsersByLastName(lastName).orElse(Collections.emptyList());
        }

        return ResponseEntity.ok(users);
    }

    // Follow a user
    @PostMapping("/{username}/followers")
    public ResponseEntity<Void> followUser(@PathVariable String username, @RequestBody FollowRequest followRequest) {
        // use userRepo to get id from username
        Integer userId = userService.searchUsersByUsername(username).orElse(null).getUserId();

        followService.followUser(Long.valueOf(userId), followRequest);
        return ResponseEntity.noContent().build();
    }

    // Unfollow a user
    @DeleteMapping("/{username}/followers")
    public ResponseEntity<Void> unfollowUser(@PathVariable String username, @RequestBody FollowRequest unfollowRequest) {
        // use userRepo to get id from username
        Integer userId = userService.searchUsersByUsername(username).orElse(null).getUserId();

        followService.unfollowUser(Long.valueOf(userId), unfollowRequest);
        return ResponseEntity.noContent().build();
    }
}
