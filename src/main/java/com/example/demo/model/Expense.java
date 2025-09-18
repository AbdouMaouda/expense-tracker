package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Assign the value of the ID automatically
    private Long id;
    private int ExpenseType;
    private String date;
    private double amount;
    private String category;
    private String account;
    private String note;

}
