package com.example.demo.controller;


import com.example.demo.model.AppUser;
import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import com.example.demo.service.ExpenseServiceImplementation;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping("/expenses/day/{date}")
    public ResponseEntity<List<Expense>> getExpensesByDay(@PathVariable String date, Authentication authentication) {

        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);

        List<Expense> expenses = expenseService.getExpensesByDay(date, appUser.getId());
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expense/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndMonth(@PathVariable String category,
                                                                       @RequestParam String month, Authentication authentication) {

        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);
        List<Expense> expenses = expenseService.getExpensesByCategoryAndMonth(category, month, appUser.getId());
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenses(Authentication authentication) {

        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);
        List<String> categories = expenseService.getAllExpensesCategory(appUser.getId());

        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);

        return ResponseEntity.ok(expenseService.getExpenseById(id, appUser.getId()));
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Authentication authentication) {
        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);

        Expense newExpense = expenseService.addExpense(expense, appUser.getId());
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,
                                                 @RequestBody Expense expense, Authentication authentication) {

        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);

        expense.setId(id);
        boolean isUpdated = expenseService.updateExpense(expense, appUser.getId());
        if (isUpdated) {
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        AppUser appUser = userService.getUserByUsername(username);

        boolean isDeleted = expenseService.deleteExpense(id, appUser.getId());
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
