package com.example.accountbook.Service;

import com.example.accountbook.Entity.MyUserDetails;
import com.example.accountbook.Entity.UserEntity;
import com.example.accountbook.Repository.UserRepository;
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
        return null;
    }
}