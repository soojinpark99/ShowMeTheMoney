package com.example.accountbook.DAO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CalendarDTO {
    private int id;
    private LocalDate date; //yyyy-mm-dd
    private String division; //income or expense
    private int money;
    private String category;
    private String memo;

    /*
    private int month;
    private int day;
    private int calid;
    */
}
