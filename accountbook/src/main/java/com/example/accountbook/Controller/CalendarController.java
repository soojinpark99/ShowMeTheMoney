package com.example.accountbook.Controller;

import com.example.accountbook.DTO.CalendarDTO;
import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Entity.MyUserDetails;
import com.example.accountbook.Exception.UnauthorizedException;
import com.example.accountbook.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CalendarController {
    private final CalendarService calendarService;
    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/users/{username}")
    public ResponseEntity<String> saveCalendar (@RequestBody Calendar calendar) {
        calendarService.saveCal(calendar);
        return new
                ResponseEntity<>("저장되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}/transactions/{calid}")
    public ResponseEntity<String> deleteCalendar(@PathVariable int calid) {
        calendarService.deleteCal(calid);
        return new ResponseEntity<>("삭제되었습니다", HttpStatus.OK);
    }

    @PostMapping("/users/{username}/transactions")
    @ResponseBody
    public ResponseEntity<List<CalendarDTO>> loadUsersAllCal(@PathVariable String username, int calid) {
        List<CalendarDTO> caldto = calendarService.getUsersAllCal(username);
        return new ResponseEntity<>(caldto, HttpStatus.OK);
    }

    @GetMapping("/users/{username}/statics/total")
    @ResponseBody
    public Map<String, Object> totalIncome(@PathVariable("username") String username,
                                           @RequestParam("year") int year,
                                           @RequestParam("month") int month,
                                           @RequestParam("divison") String division,
                                           Authentication au) {
        MyUserDetails userDetails = (MyUserDetails)au.getPrincipal();
        String currentUsername = userDetails.getUsername();

        //url의 username과 현재 로그인한 username이 다르면 예외처리
        if (!currentUsername.equals(username)) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
        }

        int[] total = calendarService.monthlyTotal(username,year,month,division);

        Map<String,Object> response = new HashMap<>();
            response.put("year", year);
            response.put("month", month);
            response.put("expense-total", total[1]); //지출 총계
            response.put("income-total",total[0]); //수입 총계

        return response;
    }
}

