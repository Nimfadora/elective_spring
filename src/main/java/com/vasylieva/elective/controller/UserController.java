package com.vasylieva.elective.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
//
//    @RequestMapping("/user")
//    public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new User(counter.incrementAndGet(), String.format(template, name), "no pass", Role.STUDENT);
//    }



}
