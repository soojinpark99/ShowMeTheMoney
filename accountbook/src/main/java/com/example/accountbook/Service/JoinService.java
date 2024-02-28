package com.example.accountbook.Service;


import com.example.accountbook.DAO.JoinDTO;
import com.example.accountbook.Entity.UserEntity;
import com.example.accountbook.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 로직
    public void joinProcess(JoinDTO joinDTO) {

        boolean isDuplicate = userRepository.existsByUsername(joinDTO.getUsername());
        if(isDuplicate) {return;}

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
