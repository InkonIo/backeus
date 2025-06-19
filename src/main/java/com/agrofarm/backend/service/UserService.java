package com.agrofarm.backend.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrofarm.backend.entity.User;
import com.agrofarm.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод, используемый в AuthController
    public void registerNewUser(String username, String email, String password) {
        if (userRepo.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
    }

    // Новый метод, используемый в UserController
    public User registerUser(String username, String email, String password) {
        if (userRepo.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    // Метод для логина, сравнивает пароль
    public User loginUser(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
