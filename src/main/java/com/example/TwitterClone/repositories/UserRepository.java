package com.example.TwitterClone.repositories;


import com.example.TwitterClone.models.orm.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);

    Optional<List<ApplicationUser>> findByFirstName(String firstName);
    Optional<List<ApplicationUser>> findByLastName(String lastName);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String username);

    Boolean deleteByUsername(String username);
}
