package com.example.accountbook.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {return "login";}

    @GetMapping("/main/username")
    public ResponseEntity<Void> currentUserName(Principal principal) {
        String username = principal.getName();
        String redirectUrl = "/main/username/"+username;
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(redirectUrl).build().toUri())
                .build();
    }


    @GetMapping( "/main/username/{username}")
    public String mainPage(@PathVariable String username) {
        return "main";
    }

}
