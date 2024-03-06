package com.example.accountbook.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidJoinProcException.class)
    public String handleInvalidJoinProcException(InvalidJoinProcException e, Model model) {
        String errorMessage = e.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
