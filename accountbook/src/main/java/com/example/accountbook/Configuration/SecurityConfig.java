package com.example.accountbook.Configuration;

import com.example.accountbook.Exception.InvalidUserAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //접근 권한 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/login/**", "/loginProc", "/join/**", "/joinProc").permitAll()//모든 사용자에게 오픈
                .requestMatchers("/write/**", "/calendar/**", "/statics/**", "/users/**").hasRole("USER") //USER role을 부여받았을떄만오픈
                .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("USER")
                .anyRequest().authenticated());

        http.formLogin(auth -> auth.loginPage("/login")
                .defaultSuccessUrl("/calendar")
                .loginProcessingUrl("/loginProc")
                .failureHandler(new MyFailureHandler())
                .permitAll());
        http.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/"));
        http.csrf((x) -> x.disable());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

