package com.example.TwitterClone.services;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApplicationUser registerUser(ApplicationUser user) {
        // log action to logger

        // gonna hash the password here or where -- not now

        return userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
