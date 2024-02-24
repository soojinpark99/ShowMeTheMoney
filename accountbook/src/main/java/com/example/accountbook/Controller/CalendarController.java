package com.example.accountbook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/write")
    public String writePage() {
        return "/write";
    }
}