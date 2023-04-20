package com.example.ServiceImpl;

import com.example.Model.Condition;
import com.example.Repository.ConditionRepository;
import com.example.Service.ConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;

    @Override
    public List<Condition> getConditions(String tariffId) {
        return this.conditionRepository.getConditionsByTariffId(tariffId);
    }

    @Override
    public Condition getConditionById(Integer id) {
        return this.conditionRepository.getConditionById(id);
    }
}
