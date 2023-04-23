package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
Entity для тарифов, хранит в себе только айди тарифа и его имя
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Tariff {
    @Id
    private String id;

    private String name;

}
