package com.example.TwitterClone.controllers;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody ApplicationUser user) {
        return  userService.registerUser(user);
    }
}
