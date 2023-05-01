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
    private String call_type;
    private Date beginning;
    private Date ending;
    private Double duration;
    private Double cost;

    public Call() {
    }

    public Call(String call_type, Date beginning, Date ending) {
        this.call_type = call_type;
        this.beginning = beginning;
        this.ending = ending;
        this.cost = 0.00;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCallType(String _call_type) {
        this.call_type = _call_type;
    }

    public void setBeginning(Date _beginning) {
        this.beginning = _beginning;
    }

    public void setEnding(Date _ending) {
        this.ending = _ending;
    }

    public String getCallType() {
        return call_type;
    }

    public Date getBeginning() {
        return beginning;
    }

    public Date getEnding() {
        return ending;
    }


    public void setDuration() {
        this.duration = (Math.ceil(this.ending.getTime() - this.beginning.getTime()) / 1000);
    }

    public Double getDurationInSec() {
        return duration;
    }

    public Integer getDurationInMin() {
        return (int) Math.ceil(duration / 60);
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public boolean isOutgoingCall() {
        if (Objects.equals(call_type, "01")) {
            return true;
        } else {
            return false;
        }
    }


}