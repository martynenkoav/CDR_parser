package com.example.Controller;

import com.example.DTO.NumberBalance;
import com.example.DTO.NumberTariff;
import com.example.Model.UserData;
import com.example.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    private final UserServiceImpl userService;

    @PatchMapping(value = "/changeTariff")
    public ResponseEntity<NumberTariff> changeTariff(@RequestBody NumberTariff numberTariff) throws IOException, ParseException {
        String number = numberTariff.getNumber();
        String tariffId = numberTariff.getTariffId();
        if (!this.userService.userExists(number)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            NumberTariff numberTariffNew = this.userService.changeTariff(number, tariffId);
            return new ResponseEntity<>(numberTariffNew, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/abonent")
    public ResponseEntity<UserData> createSubscriber(@RequestBody UserData userData) throws IOException, ParseException {
        if (this.userService.userExists(userData.getNumber())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.userService.save(userData), HttpStatus.OK);
        }
    }
}
