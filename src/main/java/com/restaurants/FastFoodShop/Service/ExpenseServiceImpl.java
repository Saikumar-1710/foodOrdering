package com.restaurants.FastFoodShop.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Expense;
import com.restaurants.FastFoodShop.Repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository) {

        this.expenseRepository =
                expenseRepository;
    }

    @Override
    public Expense saveExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesBetween(
            LocalDate fromDate,
            LocalDate toDate) {

        return expenseRepository
                .findByExpenseDateBetween(
                        fromDate,
                        toDate
                );
    }

    @Override
    public void deleteExpense(Integer id) {

        expenseRepository.deleteById(id);
    }
}
