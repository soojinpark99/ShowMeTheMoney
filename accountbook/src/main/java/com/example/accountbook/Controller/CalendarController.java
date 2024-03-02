package com.example.accountbook.Controller;

import com.example.accountbook.DAO.CalendarDTO;
import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Exception.UnauthorizedException;
import com.example.accountbook.Service.CalendarService;
import com.example.accountbook.Service.MyUserDetails;
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
    public
    CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/users/{username}/transactions")
    public ResponseEntity<String> saveCalendar (@PathVariable String username, @RequestBody CalendarDTO calendarDTO) {
        calendarService.saveCal(username, calendarDTO);

        return new
                ResponseEntity<>("저장되었습니다.", HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/users/{username}/transactions/{calid}")
    public ResponseEntity<String> deleteCalendar(@PathVariable int calid) {
        calendarService.deleteCal(calid);
        return new
                ResponseEntity<>("삭제되었습니다", HttpStatus.OK);
    }


    //조회
    @GetMapping("/users/{username}/transactions")
    public ResponseEntity<Calendar> viewCalendar(@PathVariable int calid) {
        Calendar calendar = calendarService.viewCal(calid);
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }


    //한 사용자의 모든 내역을 여러개의 w제이슨데이터로 전송
    @GetMapping("/users/{username}/transactions")
    @ResponseBody
    public ResponseEntity<List<CalendarDTO>> loadUsersAllCal(@PathVariable String username, Authentication au) {
        MyUserDetails userDetails = (MyUserDetails)au.getPrincipal();
        String currentUsername = userDetails.getUsername();
        //url의 username과 현재 로그인한 username이 다르면 예외처리
        if (!currentUsername.equals(username)) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
        }

        List<CalendarDTO> caldto = calendarService.getUsersAllCal(username);
        return new ResponseEntity<>(caldto, HttpStatus.OK);
    }

    //한 유저의 월별 총 수입/지출 통계
    @GetMapping("/users/{username}/statics/total")
    @ResponseBody
    public Map<String, Object> Monthlytotal(@PathVariable("username") String username,
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

    //한 유저의 해당 월의 카테코리별 총 수입/지출 통계
    @GetMapping(" /users/{username}/statics/category/{division}/total?year={year}&month={month}")
    @ResponseBody
    public Map<String, Integer> MonthlyCategoryTotal(@PathVariable("username") String username,
                                                     @RequestParam("year") int year,
                                                     @RequestParam("month") int month,
                                                     @PathVariable("division") String division,
                                                     Authentication au) {
        MyUserDetails userDetails = (MyUserDetails)au.getPrincipal();
        String currentUsername = userDetails.getUsername();
        if (!currentUsername.equals(username)) throw new UnauthorizedException("접근 권한이 없습니다.");
        Map<String, Integer> categoryTotal = calendarService.categoryMonthlyTotal(username,year,month,division);
        return categoryTotal;
    }
}



