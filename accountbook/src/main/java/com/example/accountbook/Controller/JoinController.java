package com.example.accountbook.Controller;

import com.example.accountbook.DAO.JoinDTO;
import com.example.accountbook.Service.JoinService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String,String> DuplicateCheck(String username) {
        Map<String,String> res = new HashMap<>();
        res.put("duplicate", String.valueOf(joinService.isDuplicateUsername(username)));
        return res;
    }
}

