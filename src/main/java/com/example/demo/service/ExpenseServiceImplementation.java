package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.utils.ExpenseDataLoader;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExpenseServiceImplementation implements ExpenseService {

    private static final AtomicLong idCounter = new AtomicLong();//use for unique id


    @Override
    public List<Expense> getExpensesByDay(String day) {
        return ExpenseDataLoader.getExpenseList().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(day))
                .toList();

    }

    @Override
    public List<Expense> getExpensesByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenseList().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category)
                        && expense.getDate().startsWith(month))
                .toList();
    }

    @Override
    public List<String> getAllExpensesCategory() {
        return ExpenseDataLoader.
                getExpenseList()
                .stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return ExpenseDataLoader.getExpenseList()
                .stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();

    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setId(idCounter.incrementAndGet());
        ExpenseDataLoader
                .getExpenseList()
                .add(expense);
        return expense;
    }

    @Override
    public boolean updateExpense(Expense Updatedexpense) {
       Optional<Expense> ExistingExpense=getExpenseById(Updatedexpense.getId());
if (ExistingExpense.isPresent()) {
    ExpenseDataLoader.getExpenseList().remove(ExistingExpense.get());
    ExpenseDataLoader.getExpenseList().add(Updatedexpense);
    return true;
}
        return false;
    }

    @Override
    public boolean deleteExpense(Long  id) {
        Optional<Expense>existingExpense=getExpenseById(id);
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenseList().remove(existingExpense.get());
            return true;
        }


        return false;
    }


}
