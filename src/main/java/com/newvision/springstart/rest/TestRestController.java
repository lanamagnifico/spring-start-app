package com.newvision.springstart.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TestRestController {
    @GetMapping
    public String sayHello() {
        return "Hello, Spring Rest! Time on server is " + LocalDateTime.now();
    }
}
