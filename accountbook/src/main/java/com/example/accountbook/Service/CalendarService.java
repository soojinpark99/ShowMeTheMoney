package com.example.accountbook.Service;

import com.example.accountbook.DAO.CalendarDTO;
import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.DAO.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository)
            {this.calendarRepository = calendarRepository;
    }

    // #CREATE #UPDATE 내역 저장, 내역 변경
    //저장 수정
    public void saveCal(String username, CalendarDTO calendarDTO) {
        Calendar calendar = new Calendar();
        calendar.setUsername(username);
        calendar.setDay(calendar.getDay());
        calendar.setDivision(calendar.getDivision());
        calendar.setMoney(calendarDTO.getMoney());
        calendar.setCategory(calendarDTO.getCategory());
        calendar.setMemo(calendar.getMemo());

        calendarRepository.save(calendar);
    }

    // #READ 내역 조회
    public Calendar viewCal(int calid) {
        return calendarRepository.findById(calid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 내역을 찾을 수 없습니다. :" + calid));
    }

    // #DELETE 내역 삭제
    public void deleteCal(int calid) {
        calendarRepository.deleteById(calid);
    }

    //사용자의 모든 내역을 dto로 변환후 list에 담아 list를 반환
    public List<CalendarDTO> getUsersAllCal(String username) {
        List<Calendar> beforeDTO = calendarRepository.findByUsername(username);
        List<CalendarDTO> afterDTO = new ArrayList<>();
        for (Calendar cal : beforeDTO) {
            afterDTO.add(toDTO(cal));
        }
        //id 역순(최신순)정렬
        Collections.sort(afterDTO, Comparator.comparingInt(CalendarDTO::getId).reversed());
        return afterDTO;
    }

    //엔티티를 DTO로 변환
    public CalendarDTO toDTO(Calendar calendar) {
        CalendarDTO dto = new CalendarDTO();
        dto.setId(calendar.getCalid());
        dto.setDate(String.format("%d-%d-%d",calendar.getYear(), calendar.getMonth(), calendar.getDay()));
        dto.setDivision(calendar.getDivision());
        dto.setMoney(calendar.getMoney());
        dto.setCategory(calendar.getCategory());
        dto.setMemo(calendar.getMemo());
        return dto;
    }

    //월별 총수입 or 총지출
    public int[] monthlyTotal(String username, int year, int month) {
        List<Calendar> calendars = calendarRepository.findByUsernameAndYearAndMonth(username,year,month);
       //total[0] = incometotal, total[1]=expensetotal
        int[] total = {0,0};
        for(Calendar cal : calendars) {
            if(cal.getDivision().equals("income")) total[0]+=cal.getMoney();
            else if(cal.getDivision().equals("expense")) total[1]+=cal.getMoney();
            else throw new IllegalArgumentException("요청이 유효하지 않습니다.");
        }
        return total;
    }

    //각각의 카테고리당 월별 지출/수입 합계
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Map<String,Integer> categoryMonthlyTotal(String username, int year, int month, String division) {

        String sql = "SELECT category,SUM(money) AS each_category_total FROM calendar" +
                            " WHERE username =? AND division = ? GROUP BY category";

        List<CalendarDTO> afterGroupBy = jdbcTemplate.query(sql, (rs, rowNum) -> {
                    CalendarDTO dto = new CalendarDTO();
                    dto.setCategory(rs.getString("category"));
                    dto.setMoney(rs.getInt("total_money"));
                    return dto;},
                    username,division);

        Map<String, Integer> categoryTotal = new HashMap<>();
        categoryTotal.put("year",year);
        categoryTotal.put("month",month);
        for (CalendarDTO dto : afterGroupBy) {
            categoryTotal.put(dto.getCategory(), dto.getMoney());
        }
        return categoryTotal;
    }

}

