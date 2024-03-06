package com.example.accountbook.Service;


import com.example.accountbook.DAO.JoinDTO;
import com.example.accountbook.Entity.UserEntity;
import com.example.accountbook.DAO.UserRepository;
import com.example.accountbook.Exception.InvalidJoinProcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //중복 회원 검사 로직
    public boolean isDuplicateUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    //회원가입 로직
    public void joinProcess(JoinDTO joinDTO) {


        String pattern = "/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$/";
        if(!joinDTO.getPassword().matches(pattern)) throw new
                InvalidJoinProcException("비밀번호가 적합하지 않습니다. 영문 알파벳, 0-9사이의 숫자, " +
                "특수문자 [!@#$%^&*] 3가지를 모두 포함해서 8글자 이상 16글자 이하의 비밀번호로 다시 시도해주세요.");

        if(!joinDTO.getPassword().equals(joinDTO.getPasswordcheck())) throw new InvalidJoinProcException(
                "입력하신 비밀번호와 비밀번호 확인 값이 다릅니다.");

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
