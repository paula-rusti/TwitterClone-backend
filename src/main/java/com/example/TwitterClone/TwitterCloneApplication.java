package com.example.TwitterClone;

import com.example.TwitterClone.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TwitterCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService) {
		return args -> {
		};
	}

}
