package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> getAllAvailableFoods() {
        return foodRepository.findAll().stream()
                .filter(Food::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findAll().stream()
                .filter(f -> f.getCategory() != null && f.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> getHighProteinFoods(double minProtein) {
        return foodRepository.findAll().stream()
                .filter(f -> f.getProtein() >= minProtein)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Food> findFoodById(Long id) {
        return id != null ? foodRepository.findById(id) : Optional.empty();
    }

    @Override
    public Optional<Food> findFoodById(Integer id) {
        return id != null ? foodRepository.findById(id.longValue()) : Optional.empty();
    }

    @Override
    public Food getFoodById(Long id) {
        return findFoodById(id).orElse(null);
    }

    @Override
    public Food getFoodById(Integer id) {
        return findFoodById(id).orElse(null);
    }

    @Override
    public void deleteFood(Long id) {
        if (id != null) foodRepository.deleteById(id);
    }

    @Override
    public void deleteFoodById(Long id) {
        deleteFood(id);
    }

    @Override
    public void deleteFood(Integer id) {
        if (id != null) foodRepository.deleteById(id.longValue());
    }

    @Override
    public void deleteFoodById(Integer id) {
        deleteFood(id);
    }
}