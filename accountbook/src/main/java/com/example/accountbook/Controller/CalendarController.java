package com.example.accountbook.Controller;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/calendar")
public class CalendarController {


    private final CalendarService calendarService;

    @Autowired
    public
    CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCalendar (@RequestBody Calendar calendar) {
        calendarService.saveCalendar(calendar);

        return new
                ResponseEntity<>("저장되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/view/{calid}")
    public ResponseEntity<Calendar> viewCalendar(@PathVariable int calid) {
        Calendar calendar = calendarService.calendarView(calid);

        return new
                ResponseEntity<>(calendar, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{calid}")
    public ResponseEntity<String> deleteCalendar(@PathVariable int calid) {
        calendarService.deleteCalendar(calid);

        return new
                ResponseEntity<>("삭제되었습니다", HttpStatus.OK);
    }

    @PostMapping("/sumHistory")
    public ResponseEntity<String> sumHistory(@RequestParam int history) {
        calendarService.SumHistory(history);

        return new
                ResponseEntity<>("history add", HttpStatus.OK);
    }

}

