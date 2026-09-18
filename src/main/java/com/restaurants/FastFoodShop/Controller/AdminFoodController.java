package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/foods")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/edit/{id}")
    public String editFoodForm(@PathVariable Long id, Model model) {
        Food food = foodService.findFoodById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
        model.addAttribute("food", food);
        return "admin/edit-food";
    }
}