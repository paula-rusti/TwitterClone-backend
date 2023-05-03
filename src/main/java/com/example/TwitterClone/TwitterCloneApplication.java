package com.example.TwitterClone;

import com.example.TwitterClone.models.ApplicationUser;
import com.example.TwitterClone.models.Role;
import com.example.TwitterClone.repositories.RoleRepository;
import com.example.TwitterClone.repositories.UserRepository;
import com.example.TwitterClone.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@SpringBootApplication
@RestController
public class TwitterCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello and welcome on Twitter!";
	}

	@Bean
	public CommandLineRunner run(RoleRepository roleRepo, UserService userService) {
		return args -> {
			roleRepo.save(new Role(1, "USER"));
			ApplicationUser u = new ApplicationUser();

			u.setFirstName("Denis");
			u.setLastName("Nutiu");

			userService.registerUser(u);
		};
	}

}
