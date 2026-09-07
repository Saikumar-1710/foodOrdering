package com.foodordering.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodordering.admin.entity.Food;
import com.foodordering.admin.repository.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // View all foods
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // View one food
    public Optional<Food> getFoodById(Integer id) {
        return foodRepository.findById(id);
    }

    // Add food
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    // Edit food
    public Food updateFood(Integer id, Food updatedFood) {

        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        existingFood.setName(updatedFood.getName());
        existingFood.setDescription(updatedFood.getDescription());
        existingFood.setPrice(updatedFood.getPrice());
        existingFood.setCategory(updatedFood.getCategory());
        existingFood.setImageUrl(updatedFood.getImageUrl());
        existingFood.setAvaliable(updatedFood.isAvaliable());

        existingFood.setProtein(updatedFood.getProtein());
        existingFood.setCalories(updatedFood.getCalories());
        existingFood.setCarbohydrates(updatedFood.getCarbohydrates());
        existingFood.setFats(updatedFood.getFats());
        existingFood.setFiber(updatedFood.getFiber());
        existingFood.setMagniseium(updatedFood.getMagniseium());

        return foodRepository.save(existingFood);
    }

    // Delete food
    public void deleteFood(Integer id) {

        if (!foodRepository.existsById(id)) {
            throw new RuntimeException("Food not found");
        }

        foodRepository.deleteById(id);
    }

    // Change availability
    public Food changeAvailability(Integer id, boolean available) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        food.setAvaliable(available);

        return foodRepository.save(food);
    }

    // Total foods
    public long getTotalFoods() {
        return foodRepository.count();
    }

    // Available foods
    public long getAvailableFoods() {
        return foodRepository.countByAvaliableTrue();
    }

    // Unavailable foods
    public long getUnavailableFoods() {
        return foodRepository.countByAvaliableFalse();
    }
}