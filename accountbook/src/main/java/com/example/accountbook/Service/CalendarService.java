package com.example.accountbook.Service;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Exception.NotFoundException;
import com.example.accountbook.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
    public void deleteCal(int calid) {
        calendarRepository.deleteById(calid);
    }

//월별 총수입 or 총지출
    public int monthlyTotal(String username, int year, int month, String type) {
        List<Calendar> calendars = calendarRepository.findByUsernameAndYearAndMonth(username,year,month);

        int total=0;
        for(Calendar cal : calendars) {
            if(type.equals("income")) total+=cal.getIncome();
            else if(type.equals("expense")) total+=cal.getExpense();
            else throw new IllegalArgumentException("요청이 유효하지 않습니다.");
        }
        return total;
    }
/*
    //월별 총수입 or 총지출. type에는 "income" or "expense"를 넣어야함
    public Map<String, Integer> monthlyTotal(int year, String type) {
        Map<String, Integer> total = new HashMap<>();
        for (int m = 1; m <= 12; m++) {
            int totalmoney = 0;
            Calendar calendar = calendarRepository.findByYearandMonth(year, m);
            if (type.equals("income")) totalmoney += calendar.getIncome();
            else if ("expense".equals(type)) totalmoney += calendar.getExpense();

            total.put(year + "-" + m, totalmoney);
        }
        return total;
    }
*/
    /*
    public Map<String, Integer> categoryTotal(int year, int month, String type, String category) {
        Map<String, Integer> categoryTotal = new HashMap<>();
        int totalmoney;

        List<Calendar> calendars = calendarRepository.findByYear(year);
        for (Calendar calendar : calendars) {
            category = calendar.getCategory();
            if ("income".equals(type)) totalmoney= calendar.getIncome();
            else if ("expense".equals(type)) totalmoney = calendar.getExpense();
            else throw new InvalidRequestStateException("유효하지 않은 요청입니다.");

            categoryTotal.put(category, categoryTotal.getOrDefault(category, 0) + totalmoney);
        }
        return categoryTotal;
    }

 */
}