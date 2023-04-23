package com.example.DTO;

import lombok.Getter;
import lombok.Setter;

/*
NumberTariff вспомогательный класс для реформатирования даты вывода.
Необходим для запроса /api/manager/changeTariff
* */

@Getter
@Setter
public class NumberTariff {
    private Long id;
    private String number;
    private String tariffId;

    public NumberTariff() {
    }

    public NumberTariff(String number, String tariffId) {
        this.number = number;
        this.tariffId = tariffId;
    }

    public NumberTariff(Long id, String number, String tariffId) {
        this.id = id;
        this.number = number;
        this.tariffId = tariffId;
    }


}
