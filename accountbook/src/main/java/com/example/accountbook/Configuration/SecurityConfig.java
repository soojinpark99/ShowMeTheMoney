package com.example.accountbook.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //접근 권한 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize)->authorize
                .requestMatchers("/","/login/**","/loginProc", "/join/**", "/joinProc").permitAll()//모든 사용자에게 오픈
               // .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/write/**","/calendar/**","/statics/**","/users/**").hasRole("USER") //USER role을 부여받았을떄만오픈
                .anyRequest().authenticated());
        http.formLogin(auth->auth.loginPage("/login")
                        .defaultSuccessUrl("/calendar")
                        .loginProcessingUrl("/loginProc")
                        .permitAll());
        http.csrf((x)->x.disable());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
