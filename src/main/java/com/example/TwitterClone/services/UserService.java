package com.example.TwitterClone.services;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.models.Role;
import com.example.TwitterClone.repositories.RoleRepository;
import com.example.TwitterClone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApplicationUser registerUser(ApplicationUser user) {
        Set<Role> roleSet = user.getAuthorities();
        roleSet.add(roleRepository.findRoleByAuthority("USER").get());
        user.setAuthorities(roleSet);

        return userRepository.save(user);
    }

}
