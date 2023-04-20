package com.example;

import com.example.Model.Call;
import com.example.Model.Condition;
import com.example.ServiceImpl.ConditionServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

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

    public void count_tariff(List<Condition> conditions) {
        System.out.println(_callList.size());
        switch (_tariffType) {
            case ("06") :
                _callList.forEach((call) -> {
                Integer maxMinutes = conditions.get(0).getMaxMinutes();
                if (_totalMinutes + call.getDurationInMin() > maxMinutes) {
                    if (_totalMinutes > maxMinutes) {
                        call.setCost(call.getDurationInMin()* conditions.get(1).getPerMinute());
                        minutesAndCost(call);
                    } else {
                        call.setCost((double) (_totalMinutes + call.getDurationInMin() - maxMinutes));
                        minutesAndCost(call);
                    }
                } else {
                    minutesAndCost(call);
                }
            });
                break;
            case ("03") :
                _callList.forEach((call) -> {
                    System.out.println("Стоимость до" + call.getCost());
                System.out.println("За минуту " + conditions.get(0).getPerMinute());
                call.setCost(call.getDurationInMin() * conditions.get(0).getPerMinute());
                minutesAndCost(call);
                System.out.println("Длительность сразговора" + call.getDurationInMin() + " Стоимость" + _totalCost);
            });
                break;
            case ("11") :
                _callList.forEach((call) -> {
                if (call.isOutgoingCall()) {
                    Integer maxMinutes = conditions.get(0).getMaxMinutes();
                    Double perMinuteUnderMax = conditions.get(0).getPerMinute();
                    Double perMinute = conditions.get(1).getPerMinute();
                    if (_totalMinutes + call.getDurationInMin() > maxMinutes) {
                        if (_totalMinutes > maxMinutes) {
                            call.setCost((call.getDurationInMin()) * perMinute);
                            minutesAndCost(call);
                        } else {
                            call.setCost((_totalMinutes + call.getDurationInMin() - maxMinutes) * perMinute + maxMinutes * perMinuteUnderMax);
                            minutesAndCost(call);
                        }
                    } else {
                        call.setCost(call.getDurationInMin() * perMinuteUnderMax);
                        minutesAndCost(call);
                    }
                }
            });
            default: {
                break;
            }
        }
    }
    }

   /* public Double getCost(CDR cdr, Condition condition){
        if (condition.getMaxMinutes() != 0) {
            if (this._totalMinutes < condition.getMaxMinutes()){
                if (cdr.getDurationInMin() + this._totalMinutes <= condition.getMaxMinutes()){
                    if (this._totalCost == condition.getFixPrice() && this._totalCost != 0) {
                        this._totalMinutes += cdr.getDurationInMin();
                    } else {
                        cdr.setCost(cdr.getDurationInMin() * condition.getPerMinute() + condition.getFixPrice());
                        this._totalMinutes += cdr.getDurationInMin();
                        this._totalCost += cdr.getCost();
                    }
                } else {
                    Integer minutesOverMax = cdr.getDurationInMin() + this._totalMinutes - condition.getMaxMinutes();
                    cdr.setCost((cdr.getDurationInMin() - minutesOverMax) * condition.getPerMinute() + condition.getFixPrice());
                    this._totalCost = cdr.getCost();
                    this._totalMinutes = cdr.getDurationInMin() - minutesOverMax;
                    Condition nextCondition = this.conditionService.getConditionById(condition.getNext());

                }
            }
        } else {
            cdr.setCost(cdr.getDurationInMin() * condition.getPerMinute() + condition.getFixPrice());
            this._totalMinutes += cdr.getDurationInMin();
            this._totalCost += cdr.getCost();
        }
    }*/
