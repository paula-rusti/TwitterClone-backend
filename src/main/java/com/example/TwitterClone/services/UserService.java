package com.example.TwitterClone.services;

import com.example.TwitterClone.mappers.ApplicationUserMapper;
import com.example.TwitterClone.mappers.FollowMapper;
import com.example.TwitterClone.models.dto.request.RegistrationRequest;
import com.example.TwitterClone.models.orm.ApplicationUser;
import com.example.TwitterClone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ApplicationUserMapper applicationUserMapper;



    @Autowired
    public UserService(UserRepository userRepository, ApplicationUserMapper applicationUserMapper) {
        this.userRepository = userRepository;
        this.applicationUserMapper = applicationUserMapper;
    }

    public ApplicationUser registerUser(RegistrationRequest user) {
        // log action to logger
        return userRepository.save(applicationUserMapper.userRegisterRequestToEntity(user));
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

    public Boolean deleteUser(String username) {
        return userRepository.deleteByUsername(username);
    }

}
