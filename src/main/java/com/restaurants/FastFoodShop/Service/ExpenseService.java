package com.restaurants.FastFoodShop.Service;

import java.time.LocalDate;
import java.util.List;

import com.restaurants.FastFoodShop.Entity.Expense;

public interface ExpenseService {

    Expense saveExpense(Expense expense);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    void deleteExpense(Integer id);
}