package com.agrofarm.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrofarm.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // для логина
    boolean existsByUsername(String username);       // для регистрации
    boolean existsByEmail(String email);             // проверка email
}
