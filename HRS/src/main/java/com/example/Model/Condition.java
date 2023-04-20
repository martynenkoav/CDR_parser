package com.example.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    private Integer id;

    private String tariffId;

    private String type;

    private Double perMinute;

    private Double fixPrice;

    private Integer maxMinutes;

}
