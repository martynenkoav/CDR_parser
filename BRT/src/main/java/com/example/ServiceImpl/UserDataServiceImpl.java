package com.example.ServiceImpl;

import com.example.DTO.NumberBalance;
import com.example.DTO.NumberTariff;
import com.example.Model.UserData;
import com.example.Repository.UserDataRepository;
import com.example.Service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;

    @Override
    public boolean userExists(String number) {
        if (this.userDataRepository.getUserByNumber(number) != null) {
            return true;
        } else {
            return false;
        }
    }

    //Увеличивает баланс пользователя на указанную сумму
    public NumberBalance pay(String number, Double money) {
        UserData user = this.userDataRepository.getUserByNumber(number);
        user.setBalance(user.getBalance() + money);
        this.userDataRepository.save(user);
        return new NumberBalance().userDataToNumberBalance(user);
    }

    //Для измнения тарифа пользователя
    public NumberTariff changeTariff(String number, String tariffId) {
        UserData user = this.userDataRepository.getUserByNumber(number);
        user.setTariffId(tariffId);
        this.userDataRepository.save(user);
        return new NumberTariff(user.getId(), user.getNumber(), user.getTariffId());
    }

    public UserData save(UserData user) {
        return this.userDataRepository.save(user);
    }

    public Long getUserDataId(String number) {
        return this.userDataRepository.getUserByNumber(number).getId();
    }

    public UserData getUserByNumber(String number) {
        return this.userDataRepository.getUserByNumber(number);
    }

}
