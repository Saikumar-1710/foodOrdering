package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.FoodService;
import com.restaurants.FastFoodShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    // Verify Admin Role
    @PostMapping("/verify-role")
    public ResponseEntity<String> verifyAdminRole(@RequestBody User user) {
        if (user != null && "ROLE_ADMIN".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.ok("Access Granted: User " + user.getUserName() + " has admin privileges.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access Denied: User does not have admin privileges.");
    }

    // User Management Endpoints
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Food Menu Management Endpoints
    @PostMapping("/foods")
    public ResponseEntity<Food> addFoodItem(@RequestBody Food food) {
        Food createdFood = foodService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<Food> updateFoodItem(@PathVariable Long id, @RequestBody Food foodDetails) {
        Food existingFood = foodService.findFoodById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        existingFood.setName(foodDetails.getName());
        existingFood.setCategory(foodDetails.getCategory());
        existingFood.setPrice(foodDetails.getPrice());
        existingFood.setAvailable(foodDetails.isAvailable());
        existingFood.setCalories(foodDetails.getCalories());
        existingFood.setProtein(foodDetails.getProtein());
        existingFood.setCarbs(foodDetails.getCarbs());
        existingFood.setFat(foodDetails.getFat());

        Food updatedFood = foodService.saveFood(existingFood);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
        foodService.deleteFoodById(id);
        return ResponseEntity.ok("Food item with ID " + id + " was deleted successfully.");
    }
}