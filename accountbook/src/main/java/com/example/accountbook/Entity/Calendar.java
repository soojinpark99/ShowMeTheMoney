package com.example.accountbook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calid;

    private int year;
    private int month;
    private int day;

    private int income;
    private int spended;
    private String category;
    private String memo;
}
