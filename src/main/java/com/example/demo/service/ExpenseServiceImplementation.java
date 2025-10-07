package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.utils.ExpenseDataLoader;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExpenseServiceImplementation implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    //private static final AtomicLong idCounter = new AtomicLong();//use for unique id

    public ExpenseServiceImplementation(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }


    @Override
    public List<Expense> getExpensesByDay(String day, Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream()
                .filter(expense -> expense.getDate().equals(day))
                .toList();


    }

    @Override
    public List<Expense> getExpensesByCategoryAndMonth(String category, String month, Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .filter(expense -> expense.getCategory()
                        .equalsIgnoreCase(category)
                        && expense.getDate().startsWith(month))
                .toList();
    }

    @Override
    public List<String> getAllExpensesCategory(Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id, Long userId) {
        return expenseRepository.findByIdAndUserId(id, userId)
                .stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();

    }

    @Override
    public Expense addExpense(Expense expense, Long userId) {
        Optional<AppUser> userOptional = userService.findUserById(userId);

        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            expense.setUser(user);
            return expenseRepository.save(expense);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public boolean updateExpense(Expense updatedExpense, Long userId) {
        Optional<Expense> ExistingExpense = expenseRepository.findByIdAndUserId(updatedExpense.getId(), userId);
        if (ExistingExpense.isPresent()) {
            updatedExpense.setUser(ExistingExpense.get().getUser());
            expenseRepository.save(updatedExpense);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id, Long userId) {
        Optional<Expense> existingExpense = expenseRepository.findByIdAndUserId(id, userId);
        if (existingExpense.isPresent()) {
            expenseRepository.deleteById(id);
            return true;
        }


        return false;
    }

    @Override
    public List<Expense> getAllUserExpenses(Long userId) {
return new ArrayList<>(
        expenseRepository.
                findByUserIdOrderByDateDesc(userId));

        }

    }


