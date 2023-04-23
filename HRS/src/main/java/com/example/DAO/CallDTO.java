package com.example.DAO;

import com.example.FormatData.FormatData;
import com.example.Model.Call;
import lombok.Getter;
import lombok.Setter;

/*
Класс, необходимый для реформатирования информации о звонке
для запроса /api/abonent/report/{numberPhone}
 */

@Getter
@Setter
public class CallDTO {
    private String callType;
    private String startTime;
    private String endTime;
    private String duration;
    private Double cost;

    public CallDTO(Call call) {
        this.callType = call.get_call_type();
        this.startTime = FormatData.formattedDate(call.get_beginning());
        this.endTime = FormatData.formattedDate(call.get_ending());
        this.duration = FormatData.secToStr(call.getDurationInSec());
        this.cost = call.get_cost();
    }
}
