package com.example.DAO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReportPayload {
    private Long id;
    private String number;
    private String tariffIndex;
    private List<CallDTO> payload = new ArrayList<>();
    private Double totalCost;
    private final String monetaryUnit = "rubles";

    public ReportPayload(Long id, String number, String tariffIndex, List<CallDTO> payload){
        this.id = id;
        this.number = number;
        this.tariffIndex = tariffIndex;
        this.payload = payload;
        this.totalCost = 0.00;
    }
    public void countTotalCost(){
        for (CallDTO callDTO: payload){
            this.totalCost += callDTO.getCost();
        }
    }

}
