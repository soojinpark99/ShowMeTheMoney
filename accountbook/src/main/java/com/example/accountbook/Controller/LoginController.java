package com.example.accountbook.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {return "login";}

    @GetMapping("/main/username")
    public ResponseEntity<Void> UserName(Principal principal) {
        String username = principal.getName();
        String redirectUrl = "/main/username/"+username;
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(redirectUrl).build().toUri())
                .build();
    }

    @GetMapping( "/main/username/{username}")
    public String mainPage(@PathVariable String username) {
      //url 보안검증방식과 세션 관리 방식 중 url 보안검증 채택
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if(!username.equals(currentUsername)) {
            return "redirect:/error";
        }


        return "main";
    }

}
