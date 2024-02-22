package com.example.accountbook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
   // private final AuthenticationManager authenticationManager;

   // public LoginController(AuthenticationManager authenticationManager) {this.authenticationManager = authenticationManager;}

    @GetMapping("/login")
    public String loginPage() {return "login";}

    @GetMapping("/join")
    public String joinPage() {return "join";}

    /*
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            session.setAttribute("user", authenticationResponse.getPrincipal());

            // 로그인 후에는 홈페이지로 리다이렉션
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/main").build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    public record LoginRequest(String username, String password) {}

     */
}
