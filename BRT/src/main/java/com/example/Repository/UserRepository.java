package com.example.Repository;

import com.example.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {

    UserData getUserByNumber(String number);

}