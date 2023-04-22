package com.example.Controller;

import com.example.DTO.NumberBalance;
import com.example.ServiceImpl.UserDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/abonent")
@PreAuthorize("hasRole('ROLE_ABONENT')")
public class AbonentController {

    private final UserDataServiceImpl userService;
    @PatchMapping(value = "/pay")
    public ResponseEntity<NumberBalance> pay(@RequestBody NumberBalance numberBalance) throws IOException, ParseException {
        String number = numberBalance.getNumber();
        Double money = numberBalance.getBalance();
        if (!this.userService.userExists(number) || money <= 0 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            NumberBalance numberBalanceNew = this.userService.pay(number, money);
            return new ResponseEntity<>(numberBalanceNew, HttpStatus.OK);
        }
    }

}
