package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("DataBase")
public class ExenseServiceImplDb implements ExpenseService {
    private ExpenseRepository repository;

    public ExenseServiceImplDb(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Expense> getExpensesByDay(String day) {
        return repository.findAll().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(day))
                .toList();
    }

    @Override
    public List<Expense> getExpensesByCategoryAndMonth(String category, String month) {
        return repository.findAll().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category)
                        && expense.getDate().startsWith(month))
                .toList();
    }

    @Override
    public List<String> getAllExpensesCategory() {
        return repository.findAll().stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public boolean updateExpense(Expense expense) {
        if (repository.existsById(expense.getId())) {
            repository.save(expense);
            return true;
        }
        ;
        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
