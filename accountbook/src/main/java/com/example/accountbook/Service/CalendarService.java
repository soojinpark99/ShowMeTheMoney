package com.example.accountbook.Service;

import com.example.accountbook.Entity.Calendar;
import com.example.accountbook.Exception.NotFoundException;
import com.example.accountbook.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

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

}