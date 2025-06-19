package com.agrofarm.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpServletRequest request) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        request.getSession(true)
               .setAttribute("SPRING_SECURITY_CONTEXT", new SecurityContextImpl(authentication));

        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    @PostMapping("/register")
public ResponseEntity<?> register(@RequestParam String username,
                                  @RequestParam String password) {
    // ⚠️ Тут только для примера. На практике надо сохранять в базу.
    return ResponseEntity.ok(Map.of("message", "Registration successful"));
}
}

