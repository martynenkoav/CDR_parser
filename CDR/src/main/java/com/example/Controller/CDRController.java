package com.example.Controller;


import com.example.Action;
import com.example.DTO.NumberBalance;
import com.example.ServiceImpl.ParserImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CDRController {

    private final ParserImpl parserImpl;

    @PostConstruct
    @RequestMapping(value = "/billing")
    public ResponseEntity<List<NumberBalance>> billing() throws IOException, ParseException {
        File file = new ClassPathResource("static/cdr.txt").getFile();
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<NumberBalance> numbers = this.parserImpl.parse(file);
            return new ResponseEntity<>(numbers, HttpStatus.OK);
        }
    }

}
