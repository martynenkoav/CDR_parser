package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

enum TARIFF_NAME {
    UNLIMITTED,
    PERMINUTE,
    BASIC,
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    private String id;

    private TARIFF_NAME name;


}
