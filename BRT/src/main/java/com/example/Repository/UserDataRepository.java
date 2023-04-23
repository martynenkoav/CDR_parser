package com.example.Repository;

import com.example.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Метод для вытаскивания UserData по номеру телефона
 */
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData getUserByNumber(String number);

}