package com.example.accountbook.Service;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Exception.NotFoundException;
import com.example.accountbook.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // #CREATE #UPDATE 내역 저장, 내역 변경
    public void saveCal(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    // #READ 내역 조회
    public Calendar viewCal(int calid) {
        return calendarRepository.findById(calid)
                .orElseThrow(() -> new NotFoundException("해당하는 내역을 찾을 수 없습니다. " + calid));
    }
    // #DELETE 내역 삭제
    public void deleteCal(int calid) {calendarRepository.deleteById(calid);}

    //월별 총수입 or 총지출. type에는 "income" or "expense"를 넣어야함
    public Map<String,Integer> monthlyTotal(int year,String type) {
        Map<String,Integer> total = new HashMap<>();
        for(int m=1; m<=12; m++) {
            int totalmoney = 0;
            List<Calendar> calendars = calendarRepository.findByYearandMonth(year,m);
            for (Calendar calendar : calendars) {
                if("income".equals(type)) totalmoney+=calendar.getIncome();
                else if ("expense".equals(type)) totalmoney += calendar.getExpense();
            }
            total.put(year + "-" + m, totalmoney);
        }
        return total;
    }
}