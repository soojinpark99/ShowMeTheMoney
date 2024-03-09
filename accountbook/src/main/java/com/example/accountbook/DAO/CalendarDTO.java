package com.example.accountbook.DAO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CalendarDTO {
    private int id;
    private String date; //yyyy-mm-dd
    private String division; //income or expense
    private int money;
    private String category;
    private String memo;

    //추가
 //   private int month;
  //  private int day;
   // private int calid;
}
