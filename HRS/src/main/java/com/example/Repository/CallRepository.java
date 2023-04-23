package com.example.Repository;

import com.example.Model.Call;
import com.example.Model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {

    List<Call> getAllByNumber(String number);

}
