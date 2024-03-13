package com.example.accountbook.Configuration;

import com.example.accountbook.Configuration.MyUserDetails;
import com.example.accountbook.Entity.UserEntity;
import com.example.accountbook.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userData = userRepository.findByUsername(username);
        if(userData!=null) {return new MyUserDetails(userData);}
        else throw new UsernameNotFoundException("입력하신 아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}
