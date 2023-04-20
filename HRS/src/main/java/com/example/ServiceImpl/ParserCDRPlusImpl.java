package com.example.ServiceImpl;

import com.example.Model.Call;
import com.example.FormatData.FormatData;
import com.example.Service.ParserCDRPlus;
import com.example.UserCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParserCDRPlusImpl implements ParserCDRPlus {

    private final ConditionServiceImpl conditionService;

    public void parse(File file) throws IOException, ParseException {
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
            Call cur_call = new Call(words[0], FormatData.convert(words[2]), FormatData.convert(words[3]));
            cur_call.setDuration();
            map.get(words[1]).addCdr(cur_call);
        }
        br.close();
        map.forEach((k, v) -> v.sortCallsList());
        map.forEach((k, v) -> v.count_tariff(this.conditionService.getConditions(v.getTariffType())));
        File newFile = new ClassPathResource("counted.txt").getFile();
        newFile.setWritable(true);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
        map.forEach((k, v) -> {
                try {
                    System.out.println("номер из hrs" + k + "\n");
                    writer.write(k + " " + v.getTotalCost() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        writer.close();
    }
}
