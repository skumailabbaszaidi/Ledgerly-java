package com.kumail.ledgerly.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Table (name = "users")
@Data 
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false )
    private String password;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private LocalDateTime createdAt;

    @PrePersist 
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
