package ru.javawebinar.basejava;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainDate {

    public static final LocalTime lt = LocalTime.now();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - start);

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        System.out.println(ld + " - " + lt);
        System.out.println(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY HH:mm");
        System.out.println(sdf.format(date));

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/YY HH:mm");
        System.out.println(dtf.format(ldt));
    }
}

