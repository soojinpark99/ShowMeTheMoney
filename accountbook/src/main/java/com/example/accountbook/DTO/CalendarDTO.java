package com.example.accountbook.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarDTO {
    private int id;
    private String date; //yyyy-mm-dd
    private String division; //income or expense
    private int money;
    private String category;
    private String memo;
}
