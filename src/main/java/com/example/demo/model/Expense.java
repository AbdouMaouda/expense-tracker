package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data//Used to create getter,setter,ToString,HashCode and the equal method
public class Expense {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("expenseType")
    private int ExpenseType;

    @JsonProperty("date")
    private String date;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("category")
    private String category;

    @JsonProperty("account")
    private String account;

    @JsonProperty("note")
    private String note;




}
