package com.galeno.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/error")
public class AppErrorController implements ErrorController {

    @ResponseBody
    public String error() {
        return "404";
    }
}
