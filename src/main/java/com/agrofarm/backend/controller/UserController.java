package com.agrofarm.backend.controller;

import com.agrofarm.backend.dto.LoginRequest;
import com.agrofarm.backend.dto.RegisterRequest;
import com.agrofarm.backend.entity.User;
import com.agrofarm.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Registered user: " + user.getUsername());
    }

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Welcome, " + user.getUsername());
    }

}
