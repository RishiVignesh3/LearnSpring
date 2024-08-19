package com.example.Student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloAll")
public class HelloAll {

    @GetMapping
    public String getHelloAll() {
        return "ReactExample";
    }
}
