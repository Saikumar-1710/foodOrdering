package com.restaurants.FastFoodShop.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurants.FastFoodShop.Entity.Expense;

public interface ExpenseRepository
        extends JpaRepository<Expense, Integer> {

    List<Expense> findByExpenseDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );
}