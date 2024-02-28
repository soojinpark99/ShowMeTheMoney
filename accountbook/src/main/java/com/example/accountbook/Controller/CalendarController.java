package com.example.accountbook.Controller;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Entity.MyUserDetails;
import com.example.accountbook.Exception.UnauthorizedException;
import com.example.accountbook.Repository.CalendarRepository;
import com.example.accountbook.Service.CalendarService;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalendarController {
    private final CalendarService calendarService;
    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/username/{username}")
    public ResponseEntity<String> saveCalendar (@RequestBody Calendar calendar) {
        calendarService.saveCal(calendar);
        return new
                ResponseEntity<>("저장되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Calendar> viewCalendar(@PathVariable int calid) {
        Calendar calendar = calendarService.viewCal(calid);

        return new
                ResponseEntity<>(calendar, HttpStatus.OK);
    }

    @DeleteMapping("/username/{username}/transactions/{calid}")
    public ResponseEntity<String> deleteCalendar(@PathVariable int calid) {
        calendarService.deleteCal(calid);
        return new ResponseEntity<>("삭제되었습니다", HttpStatus.OK);
    }

    @GetMapping("/username/{username}/statics/total")
    @ResponseBody
    public Map<String, Object> totalIncome(@PathVariable("username") String username,
                                           @RequestParam("year") int year,
                                           @RequestParam("month") int month,
                                           @RequestParam("type") String type,
                                           Authentication au) {
        MyUserDetails userDetails = (MyUserDetails)au.getPrincipal();
        String currentUsername = userDetails.getUsername();

        //url의 username과 현재 로그인한 username이 다르면 예외처리
        if (!currentUsername.equals(username)) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
        }

        int total = calendarService.monthlyTotal(username,year,month,type);
        Map<String,Object> response = new HashMap<>();
            response.put("year", year);
            response.put("month", month);
            response.put("type",type);
            response.put("total",total);

        return response;
    }
}

