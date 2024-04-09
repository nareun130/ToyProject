package com.nareun.mallapikotlin.controller.formatter

import org.springframework.context.annotation.Configuration
import org.springframework.format.Formatter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

// LocalDate와 LocalDateTime 포맷을 통합시켜주는 formatter
@Configuration
class LocalDateFormatter : Formatter<LocalDate> {


    override fun parse(text: String, locale: Locale): LocalDate {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    override fun print(localDate: LocalDate, locale: Locale): String {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate)
    }

}