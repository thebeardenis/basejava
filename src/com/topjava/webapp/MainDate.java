package com.topjava.webapp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainDate {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - start);
        Calendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println(cal.getTime());

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(sdf.format(date));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(dtf.format(ldt));
    }
}
