package com.example.accountbook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping( "/main")
    public String mainPage() {
        return "main";
    }
}