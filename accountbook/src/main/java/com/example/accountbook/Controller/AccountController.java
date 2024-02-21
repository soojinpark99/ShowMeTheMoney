package com.example.accountbook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping( "/main")
    public String mainPage() {
        return "main";
    }
}