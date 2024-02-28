/*
package com.example.accountbook.Service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String getCurrentUsername() {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 로그인되어 있지 않으면 null 반환
        if (authentication == null || !authentication.isAuthenticated()) {
            return "error";
        }

        // Principal 가져오기
        Object principal = authentication.getPrincipal();

        // Principal이 UserDetails를 구현한 경우 UserDetails로 캐스팅하여 사용자 이름 가져오기
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }

        // Principal이 UserDetails를 구현하지 않은 경우, principal의 toString() 메소드로 사용자 이름 가져오기
        return principal.toString();
    }
}

 */