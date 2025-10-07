package com.example.demo.repository;

import com.example.demo.model.Expense;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    Optional<Expense> findByIdAndUserId(Long id,Long userId);
}
