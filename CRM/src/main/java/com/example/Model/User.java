package com.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Entity
@Data
@Table(	name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Long userDataId;

    private String roles;

}