package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.Food;

public interface FoodService {

	//business logic
	
	Food saveFood(Food food);
	Food updateFood(Food food);
	
	void deleteFood(Integer id);
	Optional<Food> getFoodById(Integer id);
	
	List<Food> getAllFoods();
	List<Food> getAvaliableFoods();
	
	List<Food> getFoodsByCategory(String category);
	List<Food> getFoodsByProtien();

}
