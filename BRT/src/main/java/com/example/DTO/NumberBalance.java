package com.example.DTO;

import com.example.Model.UserData;
import lombok.Getter;
import lombok.Setter;
/*
NumberBalance вспомогательный класс для реформатирования даты вывода.
Необходим для запроса /api/manager/billing
* */
@Getter
@Setter
public class NumberBalance {
    private String number;
    private Double balance;

    public NumberBalance() {

    }

    public NumberBalance(String number, Double balance) {
        this.number = number;
        this.balance = balance;
    }

    public NumberBalance userDataToNumberBalance(UserData userData) {
        NumberBalance numberBalance = new NumberBalance(userData.getNumber(), userData.getBalance());
        return numberBalance;
    }

}
