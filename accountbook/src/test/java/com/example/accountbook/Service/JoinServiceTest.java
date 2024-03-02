package com.example.accountbook.Service;

import com.example.accountbook.DAO.JoinDTO;
import com.example.accountbook.DAO.UserRepository;
import com.example.accountbook.Entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class JoinServiceTest {

    @Test
    void joinProcess() {
      JoinService joinService = new JoinService();
        JoinDTO dto1 = new JoinDTO();
        JoinDTO dto2 = new JoinDTO();
        dto1.setUsername("minwoo");
        dto1.setPassword("abcdef");
        joinService.joinProcess(dto1);
    }
}