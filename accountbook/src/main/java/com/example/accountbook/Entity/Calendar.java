package com.example.accountbook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Calendar {
    @Id
    private int calid;

    private int income;
    private int spended;
    private String category;
    private String memo;
}
