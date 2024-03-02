package com.example.accountbook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calid;
    public String username;
    private int year;
    private int month;
    private int day;

    private String division; //income or expense
    private int money;
    private String category;
    private String memo;
}
