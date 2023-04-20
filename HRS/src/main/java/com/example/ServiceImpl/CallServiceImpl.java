package com.example.ServiceImpl;

import com.example.Model.Call;
import com.example.Repository.CallRepository;
import com.example.Repository.ConditionRepository;
import com.example.Service.CallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;

    @Override
    public void save(Call call) {
        this.callRepository.save(call);
    }
}
