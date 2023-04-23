package com.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

/*
    клас, который необзодим для хранения информации о звонке
* */

@Data
@AllArgsConstructor
@ToString
@Entity
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String _call_type;
    private Date _beginning;
    private Date _ending;
    private Double _duration;
    private Double _cost;

    public Call() {
    }

    public Call(String call_type, Date beginning, Date ending) {
        this._call_type = call_type;
        this._beginning = beginning;
        this._ending = ending;
        this._cost = 0.00;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCallType(String _call_type) {
        this._call_type = _call_type;
    }

    public void setBeginning(Date _beginning) {
        this._beginning = _beginning;
    }

    public void setEnding(Date _ending) {
        this._ending = _ending;
    }

    public String getCallType() {
        return _call_type;
    }

    public Date getBeginning() {
        return _beginning;
    }

    public Date getEnding() {
        return _ending;
    }


    public void setDuration() {
        this._duration = (Math.ceil(this._ending.getTime() - this._beginning.getTime()) / 1000);
    }

    public Double getDurationInSec() {
        return _duration;
    }

    public Integer getDurationInMin() {
        return (int) Math.ceil(_duration / 60);
    }

    public Double getCost() {
        return _cost;
    }

    public void setCost(Double cost) {
        this._cost = cost;
    }

    public boolean isOutgoingCall() {
        if (Objects.equals(_call_type, "01")) {
            return true;
        } else {
            return false;
        }
    }


}