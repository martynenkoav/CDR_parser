package com.example.FormatData;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
    в этой классе собраны все методы, которые изменяют формат данных, либо для даты, либо для времени, либо для цены
* */

public class FormatData {

    public static Date convert(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Russia/Moscow"));
        return formatter.parse(dateInString);
    }

    public static String formattedDate(Date date) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatForDate.format(date);
    }

    public static String secToStr(double duration) {
        return String.format("%02d:%02d:%02d", (int) duration / 3600, (int) duration / 60 % 60, (int) duration % 60);
    }

    public static String formatCost(double cost) {
        return new DecimalFormat("#0.00").format(cost).replace(',', '.');
    }

}