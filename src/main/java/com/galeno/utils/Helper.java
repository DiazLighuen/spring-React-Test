package com.galeno.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Setter
@Getter
public class Helper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value( "${beggin.date.string}" )
    private String begginDateString;

    @Value( "${end.date.string}" )
    private String endDateString ;

    private static final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value( "${special.month}" )
    private String specialMonthString;

    public Boolean isPromotionalDate(){
        LocalDate begginDate = LocalDate.parse(begginDateString,formatter);
        LocalDate endDate = LocalDate.parse(endDateString,formatter);

        return LocalDate.now().isEqual(begginDate) || (LocalDate.now().isBefore(begginDate) && LocalDate.now().isAfter(endDate)) || LocalDate.now().isEqual(endDate);
    }

    public Boolean isSpecialMonth(){
        LocalDate specialMonth = LocalDate.parse(specialMonthString,formatter);
        return LocalDate.now().getMonth().equals(specialMonth.getMonth());
    }

    public Boolean isToday(Date date){
        Instant instant1 = new Date().toInstant().truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = date.toInstant().truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }

}
