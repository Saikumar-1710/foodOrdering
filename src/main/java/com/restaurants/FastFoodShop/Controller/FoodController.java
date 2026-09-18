package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFoods() {
        // Filter the available foods directly from getAllFoods()
        List<Food> availableFoods = foodService.getAllFoods()
                .stream()
                .filter(Food::isAvailable)
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableFoods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        if (food == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food);
    }
}