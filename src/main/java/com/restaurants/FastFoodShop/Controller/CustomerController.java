package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.FoodService;
import com.restaurants.FastFoodShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @GetMapping("/foods")
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllAvailableFoods());
    }

    @GetMapping("/foods/category")
    public ResponseEntity<List<Food>> getFoodsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(foodService.getFoodsByCategory(category));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getCustomerProfile(@PathVariable Long id) {
        // Ensuring ID is handled as Long across calls
        User user = userService.getUserById(id).orElse(null);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }
}