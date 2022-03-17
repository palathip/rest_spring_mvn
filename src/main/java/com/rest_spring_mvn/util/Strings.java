package com.rest_spring_mvn.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Strings {
    private Strings() {
        super();
    }

    public static Date toDate(String s, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(s, formatter);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(String s) {
        return Strings.toDate(s, "d/M/y");
    }

    public static Double toDouble(String s) {
        return (s == null|| s.equals("")) ? null : Double.parseDouble(s);
    }
}
