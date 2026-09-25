package com.restaurants.FastFoodShop.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.restaurants.FastFoodShop.Entity.Expense;
import com.restaurants.FastFoodShop.Service.ExpenseService;

@Controller
@RequestMapping("/admin/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseService expenseService) {

        this.expenseService =
                expenseService;
    }

    @GetMapping
    public String expenseList(Model model) {

        List<Expense> expenses =
                expenseService.getAllExpenses();

        model.addAttribute(
                "expenses",
                expenses
        );

        return "admin/expenses";
    }

    @GetMapping("/add")
    public String addExpense(Model model) {

        Expense expense = new Expense();

        expense.setExpenseDate(
                LocalDate.now()
        );

        model.addAttribute(
                "expense",
                expense
        );

        return "admin/add-expense";
    }

    @PostMapping("/save")
    public String saveExpense(
            @ModelAttribute("expense")
            Expense expense) {

        expenseService.saveExpense(expense);

        return "redirect:/admin/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(
            @PathVariable Integer id) {

        expenseService.deleteExpense(id);

        return "redirect:/admin/expenses";
    }
}