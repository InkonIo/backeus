package com.agrofarm.backend.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrofarm.backend.entity.User;
import com.agrofarm.backend.repository.UserRepository;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public User registerUser(String username, String email, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role("USER")
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User loginUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}