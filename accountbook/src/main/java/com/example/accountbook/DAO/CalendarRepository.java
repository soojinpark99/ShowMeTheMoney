package com.example.accountbook.DAO;

import com.example.accountbook.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Integer> {
    List<Calendar> findByUsername(String username);
    List<Calendar> findByUsernameAndYearAndMonth(String username, int year, int month);
    List<Calendar> findByUsernameAndYearAndMonthAndCategory(String username, int year, int month, String category);
}