package com.example.Controller;


import com.example.ServiceImpl.ParserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CDRController {

    private final ParserImpl parserImpl;

    @RequestMapping(value = "/cdr", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> post() throws IOException, ParseException {
        File file = new ClassPathResource("static/cdr.txt").getFile();
        if (!file.exists()) {
            return new ResponseEntity<>("llll",  HttpStatus.BAD_REQUEST);
        } else {
            this.parserImpl.parse(file);
            return new ResponseEntity<>("File was send to BRT", HttpStatus.OK);
        }
    }

}
