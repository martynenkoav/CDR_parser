package com.example.DAO;

import com.example.Model.Condition;
import com.example.ServiceImpl.CallServiceImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TariffDTO {

    private Integer id;

    private String name;

    private List<Condition> conditions;

    public TariffDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
