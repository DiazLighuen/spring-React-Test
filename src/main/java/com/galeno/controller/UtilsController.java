package com.galeno.controller;

import com.galeno.dto.UserDTO;
import com.galeno.model.User;
import com.galeno.service.UtilsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utils")
@Getter
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/begginDate")
    public ResponseEntity begginDate(@RequestBody String date){
        getUtilsService().begginDate(date);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/endDate")
    public ResponseEntity endDate(@RequestBody String date){
        getUtilsService().endDate(date);
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

    private ResponseEntity<String> okResponse(String src) {
        return ResponseEntity.ok(src);
    }


}
