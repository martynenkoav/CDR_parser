package com.example.Repository;

import com.example.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData getUserByNumber(String number);

}