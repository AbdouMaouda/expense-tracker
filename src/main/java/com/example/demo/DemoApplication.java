package com.example.demo;

import com.example.demo.model.Expense;
import com.example.demo.utils.ExpenseDataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
@Override
    public void run(String...args){
    List<Expense>expenseList=ExpenseDataLoader.getExpenseList();
    expenseList.forEach(System.out::println);
}
}
