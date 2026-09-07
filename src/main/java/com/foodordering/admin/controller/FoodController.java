package com.foodordering.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodordering.admin.entity.Food;
import com.foodordering.admin.service.FoodService;

@RestController
@RequestMapping("/api/admin/foods")
@CrossOrigin(origins = "*")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 1. View all foods
    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    // 2. View food by ID
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Integer id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Add food
    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.addFood(food));
    }

    // 4. Edit food
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(
            @PathVariable Integer id,
            @RequestBody Food food) {

        try {
            return ResponseEntity.ok(foodService.updateFood(id, food));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Delete food
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Integer id) {

        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok("Food deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Change food availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<Food> changeAvailability(
            @PathVariable Integer id,
            @RequestParam boolean available) {

        try {
            return ResponseEntity.ok(
                    foodService.changeAvailability(id, available)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 7. Total foods
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalFoods() {
        return ResponseEntity.ok(foodService.getTotalFoods());
    }

    // 8. Available foods
    @GetMapping("/count/available")
    public ResponseEntity<Long> getAvailableFoods() {
        return ResponseEntity.ok(foodService.getAvailableFoods());
    }

    // 9. Unavailable foods
    @GetMapping("/count/unavailable")
    public ResponseEntity<Long> getUnavailableFoods() {
        return ResponseEntity.ok(foodService.getUnavailableFoods());
    }
}