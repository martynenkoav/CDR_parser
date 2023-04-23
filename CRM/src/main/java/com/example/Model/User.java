package com.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
Entity для информации о юзере, необходимой для спринг секьюрити
username, password, roles
 */

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