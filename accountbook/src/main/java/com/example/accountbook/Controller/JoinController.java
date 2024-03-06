package com.example.accountbook.Controller;

import com.example.accountbook.DAO.JoinDTO;
import com.example.accountbook.Service.JoinService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JoinController {
    @Autowired
    private JoinService joinService;
    @GetMapping("/join")
    public String joinPage() {return "join";}

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        System.out.println(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }

    //중복 회원인지 아닌지 보내줌
    @GetMapping("/join/username/duplication")
    @ResponseBody
    public ResponseEntity<String> DuplicateCheck(String username) {
        //중복이라서 가입못함 -> true, 중복 ㄴㄴ 가입 ㄱㄴ -> false
        boolean res = joinService.isDuplicateUsername(username);
        return new ResponseEntity<>(String.valueOf(res), HttpStatus.OK);
    }
}

