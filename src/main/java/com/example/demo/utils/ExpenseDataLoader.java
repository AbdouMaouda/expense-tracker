package com.example.demo.utils;

import com.example.demo.model.Expense;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//This class load data from a JSON file
@Component
public class ExpenseDataLoader {

    private static List<Expense> expenseList = new ArrayList<Expense>();

    @PostConstruct//The moment this class is used upload data
    public void init() {
        InputStream inputStream = getClass().getResourceAsStream("/expenseData.json");
        if (inputStream == null) throw new IllegalStateException("Not found: /expenseData.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            expenseList = objectMapper.readValue(inputStream,
                    new TypeReference<List<Expense>>() {
                    });
            System.out.println("[ExpenseDataLoader] Loaded " + expenseList.size() + " expenses");

        }

        catch (Exception e) {   // catch ANY exception
            e.printStackTrace();  // so you see the exact cause in the console
            throw new RuntimeException("Failed to load expenseData.json", e);
        }
    }

    public static List<Expense> getExpenseList() {
        return expenseList;
    }
}
