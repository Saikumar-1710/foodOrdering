package com.restaurants.FastFoodShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.FoodService;

@Controller
@RequestMapping("/admin/foods")
public class AdminFoodController {

    private final FoodService foodService;

    public AdminFoodController(
            FoodService foodService) {

        this.foodService = foodService;
    }

    @GetMapping
    public String foodList(Model model) {

        model.addAttribute(
                "foods",
                foodService.getAllFoods()
        );

        return "admin/food-list";
    }

    @GetMapping("/add")
    public String addFood(Model model) {

        Food food = new Food();

        model.addAttribute(
                "food",
                food
        );

        return "admin/add-food";
    }

    @PostMapping("/save")
    public String saveFood(
            @ModelAttribute("food") Food food) {

        foodService.saveFood(food);

        return "redirect:/admin/foods";
    }

    @GetMapping("/edit/{id}")
    public String editFood(
            @PathVariable Integer id,
            Model model) {

        Food food = foodService
                .getFoodById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Food Not Found"
                        )
                );

        model.addAttribute(
                "food",
                food
        );

        return "admin/add-food";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(
            @PathVariable Integer id) {

        foodService.deleteFood(id);

        return "redirect:/admin/foods";
    }
}