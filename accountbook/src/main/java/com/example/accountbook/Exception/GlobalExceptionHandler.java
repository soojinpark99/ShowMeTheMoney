package com.example.accountbook.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUserAccessException.class)
    public String handleInvalidJoinProcException(InvalidUserAccessException e, Model model) {
        String errorMessage = e.getMessage();
        String redirectUrl = e.getRedirectUrl();
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("redirectUrl",redirectUrl);
        return "error";
    }
}
