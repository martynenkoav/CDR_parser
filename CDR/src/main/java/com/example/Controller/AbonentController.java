package com.example.Controller;

import com.example.DAO.CallDTO;
import com.example.DAO.ReportPayload;
import com.example.DTO.NumberBalance;
import com.example.Model.Call;
import com.example.Model.UserData;
import com.example.ServiceImpl.CallServiceImpl;
import com.example.ServiceImpl.UserDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/abonent")
public class AbonentController {
    private final UserDataServiceImpl userDataService;

    private final UserDataServiceImpl userService;

    private final CallServiceImpl callService;


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


    @GetMapping(value = "/report/{numberPhone}")
    public ResponseEntity<ReportPayload> pay(@PathVariable("numberPhone") String numberPhone) throws IOException, ParseException {
        if (!this.userService.userExists(numberPhone)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            UserData userData = this.userDataService.getUserByNumber(numberPhone);
            List<Call> calls = this.callService.getAllByNumber(numberPhone);
            List<CallDTO> payload = new ArrayList<>();
            for (Call call: calls){
                CallDTO callDTO = new CallDTO(call);
                payload.add(callDTO);
            }
            ReportPayload reportPayload = new ReportPayload(userData.getId(), userData.getNumber(), userData.getTariffId(), payload);
            reportPayload.countTotalCost();
            return new ResponseEntity<>(reportPayload, HttpStatus.OK);
        }
    }


}
