package com.example.DAO;

import com.example.Model.Call;
import com.example.Model.Condition;
import com.example.Repository.CallRepository;
import com.example.ServiceImpl.CallServiceImpl;
import com.example.ServiceImpl.ConditionServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
       класс, в котором содержится список звонков пользователя, тип тарифа, итоговая стоимость и итоговое количество минут
* */



public class UserCall {
    private String _tariffType;
    private ArrayList<Call> _callList;
    private Double _totalCost;
    private Integer _totalMinutes;

    //?
    public UserCall(String tariffType){
        _tariffType = tariffType;
        _callList = new ArrayList<Call>();
        _totalCost = 0.00;
        _totalMinutes = 0;
    }

    public Double getTotalCost() {
        return _totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this._totalCost = totalCost;
    }

    public Integer getTotalMinutes() {
        return _totalMinutes;
    }

    public void setTotalMinutes(Integer totalMinutes) {
        this._totalMinutes = totalMinutes;
    }

    public String getTariffType() {
        return _tariffType;
    }

    public void setTariffType(String tariffType) {
        this._tariffType = tariffType;
    }

    public ArrayList<Call> getCallList() {
        return _callList;
    }

    public void setCallList(ArrayList<Call> _callList) {
        this._callList = _callList;
    }

    public void addCdr(Call call){
        this._callList.add(call);
    }

    public void minutesAndCost(Call call){
        _totalCost += call.getCost();
        _totalMinutes += call.getDurationInMin();
    }

    public void sortCallsList(){
        Collections.sort(_callList, Comparator.comparing(Call::getBeginning));
    }

    }

