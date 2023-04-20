package com.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberBalance {
    private String number;
    private Double balance;

    public NumberBalance(String number, Double balance){
        this.number = number;
        this.balance = balance;
    }

}
