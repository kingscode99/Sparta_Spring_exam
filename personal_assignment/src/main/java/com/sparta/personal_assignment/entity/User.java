package com.sparta.personal_assignment.entity;

import com.sparta.personal_assignment.dto.UserRequsetDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;


    public User(UserRequsetDto requsetDto) {
        this.username = requsetDto.getUsername();
        this.password = requsetDto.getPassword();
    }
}
