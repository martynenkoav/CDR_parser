package com.example.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usersData")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private Double balance;

    private String tariffId;

    public UserData(String number, String tariffId, Double balance){
        this.number = number;
        this.tariffId = tariffId;
        this.balance = balance;
    }

}
