package com.example.demo.service;

import com.example.demo.model.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {


    List<Expense> getAllUserExpenses(Long userId);
    List<Expense> getExpensesByDay(String day,Long userId);
    List<Expense> getExpensesByCategoryAndMonth(String category,
                                                String month,Long userId);
    List<String> getAllExpensesCategory(Long userId);

    Optional<Expense> getExpenseById(Long id,Long userId);
    Expense addExpense(Expense expense,Long userId);
    boolean updateExpense(Expense expense,Long userId);
    boolean deleteExpense(Long id,Long userId);
}