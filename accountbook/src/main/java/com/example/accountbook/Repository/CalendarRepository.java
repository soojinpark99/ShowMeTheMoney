package com.example.accountbook.Repository;

import com.example.accountbook.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Integer> {
    List<Calendar> findByYearandMonth(int year, int month);
}
