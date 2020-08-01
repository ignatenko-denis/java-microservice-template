package com.sample.controller;

import com.sample.util.logging.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
public class EchoController {
    @LogExecutionTime
    @RequestMapping("/echo")
    public LocalDate echo() {
        LocalDate result = LocalDate.now();
        log.info("{}", result);
        return result;
    }
}