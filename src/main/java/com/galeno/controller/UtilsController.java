package com.galeno.controller;

import com.galeno.service.UtilsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utils")
@Getter
@CrossOrigin(origins = "*")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/begginDate")
    public ResponseEntity setBegginDate(@RequestBody String date){
        getUtilsService().setBegginDate(date);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/endDate")
    public ResponseEntity setEndDate(@RequestBody String date){
        getUtilsService().setEndDate(date);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/specialMonth")
    public ResponseEntity SpecialMonth(@RequestBody String date){
        getUtilsService().setSpecialMonth(date);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/begginDate")
    public ResponseEntity<String> readBeggin(){
        return okResponse(getUtilsService().getBegginDate());
    }

    @GetMapping("/endDate")
    public ResponseEntity<String> readEnd(){
        return okResponse(getUtilsService().getEndDate());
    }

    @GetMapping("/specialMonth")
    public ResponseEntity<String> readSpecialMonth(){
        return okResponse(getUtilsService().getSpecialMonth());
    }

    private ResponseEntity<String> okResponse(String src) {
        return ResponseEntity.ok(src);
    }


}
