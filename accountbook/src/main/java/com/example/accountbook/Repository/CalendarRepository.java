package com.example.accountbook.Repository;

import com.example.accountbook.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Integer> {

}
