package com.example.TwitterClone.services;

import com.example.TwitterClone.models.orm.ApplicationUser;
import com.example.TwitterClone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<ApplicationUser> searchUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<List<ApplicationUser>> searchUsersByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public Optional<List<ApplicationUser>> searchUsersByLastName(String lastName) {
        return userRepository.findByFirstName(lastName);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
