package com.example.ServiceImpl;

import com.example.Repository.UserRepository;
import com.example.Service.Parser;
import com.example.Service.ParserCDRPlus;
import com.example.Model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

@RequiredArgsConstructor
@Slf4j
@Service

public class ParserImpl implements Parser {
    private final UserRepository userRepository;

    private final ParserCDRPlusImpl parserCDRPlus;

    public void parse(File file) throws IOException, ParseException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
       // BufferedWriter writer = new BufferedWriter(new FileWriter("./Payload/cdr+.txt", true));
        File newFile = new ClassPathResource("cdr+.txt").getFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            while((line = br.readLine()) != null){
                String lineCur = line;
                line = line.replaceAll("\\s", "");
                line = line.replaceAll(",", " ");
                String[] words = line.split(" ");
                if (this.userRepository.getUserByNumber(words[1]) != null) {
                    User curUser = this.userRepository.getUserByNumber(words[1]);
                    if (curUser.getBalance() > 0) {
                        writer.write(lineCur + ", " + curUser.getTariffId() + "\n");
                    }
                }
            }
            writer.close();
            this.parserCDRPlus.parse(newFile);
    }

    @Override
    public Double getBalanceByNumber(String number) {
        return null;
    }
}
