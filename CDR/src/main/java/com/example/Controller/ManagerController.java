package com.example.Controller;

import com.example.DTO.NumberBalance;
import com.example.DTO.NumberTariff;
import com.example.Model.User;
import com.example.Model.UserData;
import com.example.Service.UserService;
import com.example.ServiceImpl.ParserImpl;
import com.example.ServiceImpl.UserDataServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/*
Контроллер, содержащий эндпоинты для менеджера
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final ParserImpl parserImpl;

    private final UserDataServiceImpl userDataService;

    private final UserService userService;


    @PostConstruct
    @GetMapping(value = "/billing")
    public ResponseEntity<List<NumberBalance>> billing() throws IOException, ParseException {
        File file = new ClassPathResource("static/cdr.txt").getFile();
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<NumberBalance> numbers = this.parserImpl.parse(file);
            return new ResponseEntity<>(numbers, HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/changeTariff")
    public ResponseEntity<NumberTariff> changeTariff(@RequestBody NumberTariff numberTariff) throws IOException, ParseException {
        String number = numberTariff.getNumber();
        String tariffId = numberTariff.getTariffId();
        if (!this.userDataService.userExists(number)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            NumberTariff numberTariffNew = this.userDataService.changeTariff(number, tariffId);
            return new ResponseEntity<>(numberTariffNew, HttpStatus.OK);
        }
    }


    @PostMapping(value = "/abonent")
    public ResponseEntity<UserData> createSubscriber(@RequestBody UserData userData) throws IOException, ParseException {
        if (this.userDataService.userExists(userData.getNumber())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            //добавляем пользователя в две таблицы: users и users_data
            //логин и пароль равны номеру абонента
            this.userDataService.save(userData);
            Long id = this.userDataService.getUserDataId(userData.getNumber());
            User user = new User();
            user.setPassword(userData.getNumber());
            user.setUsername(userData.getNumber());
            user.setUserDataId(id);
            user.setRoles("ROLE_ABONENT");
            this.userService.save(user);
            return new ResponseEntity<>(this.userDataService.save(userData), HttpStatus.OK);
        }
    }
}
