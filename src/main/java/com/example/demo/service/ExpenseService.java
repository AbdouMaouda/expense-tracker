package com.example.demo.service;

import com.example.demo.model.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getExpensesByDay(String day);
    List<Expense> getExpensesByCategoryAndMonth(String category, String month);
    List<String> getAllExpensesCategory();

    Optional<Expense> getExpenseById(Long id);
    Expense addExpense(Expense expense);
    boolean updateExpense(Expense expense);
    boolean deleteExpense(Long id);
}