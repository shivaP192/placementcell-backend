package com.placement.service;

import com.placement.dto.LoginRequest;
import com.placement.dto.NewPasswordRequest;
import com.placement.dto.PasswordResetRequest;
import com.placement.dto.RegisterRequest;
import com.placement.model.User;
import com.placement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest request) {
        // Check if email is already in use
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        // Hash password before storing
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new user with the hashed password
        User user = new User(request.getName(), request.getEmail(), hashedPassword, request.getRole());

        // Save user to database
        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Verify hashed password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    public void forgotPassword(PasswordResetRequest request) {
        throw new UnsupportedOperationException("Forgot Password feature not implemented yet.");
    }

    public void resetPassword(NewPasswordRequest request) {
        throw new UnsupportedOperationException("Reset Password feature not implemented yet.");
    }
}
