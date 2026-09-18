package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Food;
import java.util.List;
import java.util.Optional;

public interface FoodService {
    Food saveFood(Food food);
    List<Food> getAllFoods();
    List<Food> getAllAvailableFoods();
    List<Food> getFoodsByCategory(String category);
    List<Food> getHighProteinFoods(double minProtein);

    Optional<Food> findFoodById(Long id);
    Optional<Food> findFoodById(Integer id);

    Food getFoodById(Long id);
    Food getFoodById(Integer id);

    void deleteFood(Long id);
    void deleteFoodById(Long id);
    void deleteFood(Integer id);
    void deleteFoodById(Integer id);
}