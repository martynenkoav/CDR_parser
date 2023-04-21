package com.example.DTO;

import com.example.Model.UserData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberBalance {
    private String number;
    private Double balance;

    public NumberBalance(){

    }

    public NumberBalance(String number, Double balance){
        this.number = number;
        this.balance = balance;
    }

    public NumberBalance userDataToNumberBalance(UserData userData){
        NumberBalance numberBalance = new NumberBalance(userData.getNumber(), userData.getBalance());
        return numberBalance;
    }

}
