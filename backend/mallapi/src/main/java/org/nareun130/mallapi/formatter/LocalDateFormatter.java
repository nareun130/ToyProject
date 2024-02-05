package org.nareun130.mallapi.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

//* 날짜 시간을 다루기 위한 포매터
public class LocalDateFormatter implements Formatter<LocalDate>{

    @Override
    public LocalDate parse(String text, Locale locale) {
        
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {

        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }

    
}
