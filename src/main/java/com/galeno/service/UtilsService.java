package com.galeno.service;

import com.galeno.utils.Helper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@Getter
public class UtilsService {

    @Autowired
    private Helper helper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void begginDate(String date){
        if(isValid(date)){
            helper.setBegginDateString(date);
        }
    }

    public void endDate(String date){
        if(isValid(date)){
            helper.setEndDateString(date);
        }
    }

    public String getBegginDate(){
        return getHelper().getBegginDateString();
    }

    public String getEndDate(){
        return getHelper().getEndDateString();
    }

    private boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
