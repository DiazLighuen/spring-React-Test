package com.galeno.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Setter
@Getter
public class Helper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String begginDateString = "01-11-2021";
    private String endDateString = "01-12-2021";

    public Boolean isPromotionalDate(){
        LocalDate begginDate = LocalDate.parse(begginDateString,formatter);
        LocalDate endDate = LocalDate.parse(endDateString,formatter);

        return LocalDate.now().isBefore(begginDate) && LocalDate.now().isAfter(endDate);
    }

}
