package com.example.Repository;

import com.example.Model.Condition;
import com.example.Model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository  extends JpaRepository<Condition, Integer> {
    List<Condition> getConditionsByTariffId(String tariffId);

    Condition getConditionById(Integer id);
}
