package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

enum TARIFF_NAME
{
    UNLIMITTED,
    PERMINUTE,
    BASIC,
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Tariff {
    @Id
    private Integer id;

    private TARIFF_NAME name;


}
