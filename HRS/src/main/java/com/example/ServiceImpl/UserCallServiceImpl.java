package com.example.ServiceImpl;

import com.example.Model.Call;
import com.example.FormatData.FormatData;
import com.example.DAO.UserCall;
import com.example.Model.Condition;
import com.example.Service.UserCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.parser.ParserImpl;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserCallServiceImpl implements UserCallService {

    private final ConditionServiceImpl conditionService;
    private final CallServiceImpl callService;

    //Функция, который необходим для парсинга файла cdr+.txt
    public File parse(File file) throws IOException, ParseException {
        HashMap<String, UserCall> map = new HashMap<String, UserCall>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\\s", "");
            line = line.replaceAll(",", " ");
            String[] words = line.split(" ");
            if (!map.containsKey(words[1])) {
                map.put(words[1], new UserCall(words[4]));
            }
            Call curCall = new Call(words[0], FormatData.convert(words[2]), FormatData.convert(words[3]));
            curCall.setDuration();
            curCall.setNumber(words[1]);
            map.get(words[1]).addCdr(curCall);
        }
        br.close();
        map.forEach((k, v) -> v.sortCallsList());
        map.forEach((k, v) -> count_tariff(v, this.conditionService.getConditions(v.getTariffType())));
        File newFile = new ClassPathResource("counted.txt").getFile();
        newFile.setWritable(true);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, false));
        map.forEach((k, v) -> {
            try {
                writer.write(k + " " + v.getTotalCost() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
        return newFile;
    }

    //Здесь происходит подсчет тарифа
    //Для каждого тарифа в таблице conditions содержатся условия этого тарифа
    //Данная функци осущесвляет логику подсчета тарифоф на основе условий, указанных в бд
    public void count_tariff(UserCall userCall, List<Condition> conditions) {
        String _tariffType = userCall.getTariffType();
        List<Call> _callList = userCall.getCallList();
        Integer _totalMinutes = userCall.getTotalMinutes();
        switch (_tariffType) {
            case ("06"):
                _callList.forEach((call) -> {
                    Integer maxMinutes = conditions.get(0).getMaxMinutes();
                    Double fixPrice = conditions.get(0).getFixPrice();
                    Double _totalCost = userCall.getTotalCost();
                    if (_totalMinutes + call.getDurationInMin() > maxMinutes) {
                        if (_totalMinutes > maxMinutes) {
                            call.setCost(call.getDurationInMin() * conditions.get(1).getPerMinute());
                            userCall.minutesAndCost(call);
                            this.callService.save(call);
                        } else {
                            if (_totalCost == 0) {
                                userCall.setTotalCost(fixPrice);
                            }
                            call.setCost((double) (_totalMinutes + call.getDurationInMin() - maxMinutes));
                            userCall.minutesAndCost(call);
                            this.callService.save(call);
                        }
                    } else {
                        if (_totalCost == 0) {
                            userCall.setTotalCost(fixPrice);
                            userCall.minutesAndCost(call);
                            this.callService.save(call);
                        }
                    }
                });
                break;
            case ("03"):
                _callList.forEach((call) -> {
                    call.setCost(call.getDurationInMin() * conditions.get(0).getPerMinute());
                    userCall.minutesAndCost(call);
                    this.callService.save(call);
                });
                break;
            case ("11"):
                _callList.forEach((call) -> {
                    if (call.isOutgoingCall()) {
                        Integer maxMinutes = conditions.get(1).getMaxMinutes();
                        Double perMinuteUnderMax = conditions.get(1).getPerMinute();
                        Double perMinute = conditions.get(2).getPerMinute();
                        if (_totalMinutes + call.getDurationInMin() > maxMinutes) {
                            if (_totalMinutes > maxMinutes) {
                                call.setCost((call.getDurationInMin()) * perMinute);
                            } else {
                                call.setCost((_totalMinutes + call.getDurationInMin() - maxMinutes) * perMinute + maxMinutes * perMinuteUnderMax);
                            }
                        } else {
                            call.setCost(call.getDurationInMin() * perMinuteUnderMax);
                        }
                        userCall.minutesAndCost(call);
                    }
                    this.callService.save(call);
                });
            default: {
                break;
            }
        }
    }
}
