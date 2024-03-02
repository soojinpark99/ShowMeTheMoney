package com.example.accountbook.Service;

import com.example.accountbook.DAO.CalendarDTO;
import com.example.accountbook.DAO.CalendarRepository;
import com.example.accountbook.Entity.Calendar;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class CalendarServiceTest {


    @Test
    void monthlyTotal() {
        Calendar calendar1 = new Calendar();
        calendar1.setMoney(1000);
        calendar1.setDivision("income");
        Calendar calendar2 = new Calendar();
        calendar2.setMoney(2000);
        calendar2.setDivision("expense");
        List<Calendar> calendars = new ArrayList<>() {{
            add(calendar1);
            add(calendar2);
        }};
        int[] total={0,0};
        for(Calendar cal : calendars) {
            if(cal.getDivision().equals("income")) total[0]+=cal.getMoney();
            else if(cal.getDivision().equals("expense")) total[1]+=cal.getMoney();
            else throw new IllegalArgumentException("요청이 유효하지 않습니다.");
        }
        assertEquals(1000, total[0]);
        assertEquals(2000, total[1]);
        }


    @Test
    void toDTO() {
        Calendar calendar = new Calendar();
        calendar.setYear(2024);
        calendar.setMonth(12);
        calendar.setDay(25);

        CalendarDTO dto = new CalendarDTO();
        dto.setDate(String.format("%d-%d-%d",calendar.getYear(), calendar.getMonth(), calendar.getDay()));
        assertEquals("2024-12-25", dto.getDate());
    }

}