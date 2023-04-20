package com.example.Service;

import com.example.Model.Condition;

import java.util.List;

public interface ConditionService {
    List<Condition> getConditions(String tariffId);

    Condition getConditionById(Integer id);
}
