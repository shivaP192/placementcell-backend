package com.placement.config;

import com.placement.model.User;
import com.placement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            User user = new User("Admin", "admin@example.com", "password", "+1234567890");
            
            // Remove setStatus() if it's causing errors
            // user.setStatus("ACTIVE");

            userRepository.save(user);
        };
    }
}
