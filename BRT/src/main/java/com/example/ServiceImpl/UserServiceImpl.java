package com.example.ServiceImpl;

import com.example.DTO.NumberBalance;
import com.example.DTO.NumberTariff;
import com.example.Model.UserData;
import com.example.Repository.UserRepository;
import com.example.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean userExists(String number) {
        if (this.userRepository.getUserByNumber(number) != null){
            return true;
        } else {
            return false;
        }
    }

    public NumberBalance pay(String number, Double money){
        UserData user = this.userRepository.getUserByNumber(number);
        user.setBalance(user.getBalance()+money);
        this.userRepository.save(user);
        return new NumberBalance().userDataToNumberBalance(user);
    }

    public NumberTariff changeTariff(String number, String tariffId){
        UserData user = this.userRepository.getUserByNumber(number);
        user.setTariffId(tariffId);
        this.userRepository.save(user);
        return new NumberTariff(user.getId(), user.getNumber(), user.getTariffId());
    }

    public UserData save(UserData user){
        return this.userRepository.save(user);
    }
}
