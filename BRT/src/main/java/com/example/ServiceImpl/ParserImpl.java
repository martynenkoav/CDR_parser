package com.example.ServiceImpl;

import com.example.DTO.NumberBalance;
import com.example.Repository.UserDataRepository;
import com.example.Service.Parser;
import com.example.Model.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/*
Класс, который необходим для парсинга информации из cdr.txt
И записи инофрмации в файл cdr+.txt
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class ParserImpl implements Parser {
    private final UserDataRepository userDataRepository;

    private final UserCallServiceImpl userCallService;


    //Метод для парсинга cdr.txt и записи информации в файл cdr+.txt
    public List<NumberBalance> parse(File file) throws IOException, ParseException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        File newFile = new ClassPathResource("cdr+.txt").getFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, false));
        while ((line = br.readLine()) != null) {
            String lineCur = line;
            System.out.println(lineCur);
            line = line.replaceAll("\\s", "");
            line = line.replaceAll(",", " ");
            String[] words = line.split(" ");
            if (this.userDataRepository.getUserByNumber(words[1]) != null) {
                UserData curUser = this.userDataRepository.getUserByNumber(words[1]);
                if (curUser.getBalance() > 0) {
                    writer.write(lineCur + ", " + curUser.getTariffId() + "\n");
                }
            }
        }
        writer.close();
        File fileWithBalance = this.userCallService.parse(newFile);
        parseFileWithBalance(fileWithBalance);
        return getNumbersAndBalance();

    }

    //Метод, который парсит файл приходящий из HRS, в котором
    //содержится номер и количество денег, которое необходимо списать у абонента
    //после в базе данных уменьшает значения баланса пользователей
    public void parseFileWithBalance(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.split(" ");
            UserData user = this.userDataRepository.getUserByNumber(words[0]);
            user.setBalance(user.getBalance() - Double.parseDouble(words[1]));
            this.userDataRepository.save(user);

        }
    }

    //Метод, вытаскивающий информацию о клиенте: номер и баланс
    public List<NumberBalance> getNumbersAndBalance() {
        List<NumberBalance> numbers = new ArrayList<>();
        List<UserData> users = this.userDataRepository.findAll();
        for (UserData user : users) {
            NumberBalance number = new NumberBalance(user.getNumber(), user.getBalance());
            numbers.add(number);
        }
        return numbers;
    }

}
