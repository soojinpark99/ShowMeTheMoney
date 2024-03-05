package com.example.accountbook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/error")
    public String errorPage() {return "error";}

    @GetMapping("/write")
    public String writePage() {return "write";}
}
