package com.agrofarm.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrofarm.backend.service.JwtService;
import com.agrofarm.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestParam String username,
                               @RequestParam String password,
                               HttpServletRequest request) {
    Authentication authentication = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password)
    );

    // Генерация JWT
    String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

    // Сохраняем контекст безопасности в сессию (по желанию)
    request.getSession(true)
           .setAttribute("SPRING_SECURITY_CONTEXT", new SecurityContextImpl(authentication));

    return ResponseEntity.ok(Map.of("token", token));
}


    @CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email) {
        userService.registerNewUser(username, email, password);
        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }
}
