package com.example.accountbook.Service;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    // #CREATE #UPDATE 내역 저장, 내역 변경
    public void saveCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }
    // #READ 내역 조회
    public Calendar calendarView(int calid) {return calendarRepository.findById(calid).get();}


    // #DELETE 내역 삭제
    public void deleteCalendar(int calid) {calendarRepository.deleteById(calid);}

    int sum=0;
    public void SumHistory(int history) {
        sum+=history;
    }

}