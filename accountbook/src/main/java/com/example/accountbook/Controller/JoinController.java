package com.example.accountbook.Controller;

import com.example.accountbook.Service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
}
