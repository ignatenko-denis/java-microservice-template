package com.sample.controller;

import com.sample.util.logging.LogExecutionTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    public static final String WELCOME_MESSAGE = "Welcome to Sample!";

    @LogExecutionTime
    @RequestMapping("/")
    public String index() {
        return WELCOME_MESSAGE;
    }
}