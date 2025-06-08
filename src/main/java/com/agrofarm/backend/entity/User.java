package com.agrofarm.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // Название таблицы в БД
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоинкремент
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
