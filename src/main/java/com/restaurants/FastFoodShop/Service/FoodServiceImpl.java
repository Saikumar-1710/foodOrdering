package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{

	private FoodRepository foodRepository;
	
	public FoodServiceImpl(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	@Override
	public Food saveFood(Food food) {
		return foodRepository.save(food);
	}

	@Override
	public Food updateFood(Food food) {	
		return foodRepository.save(food);
	}

	@Override
	public void deleteFood(Integer id) {
		foodRepository.deleteById(id);
	}

	@Override
	public Optional<Food> getFoodById(Integer id) {
		return 	foodRepository.findById(id);
	}

	@Override
	public List<Food> getAllFoods() {
		return foodRepository.findAll();
	}

	@Override
	public List<Food> getAvaliableFoods() {
		return foodRepository.findByAvailableTrue();
	}

	@Override
	public List<Food> getFoodsByCategory(String category) {
		return foodRepository.findByCategoryAndAvailableTrue(category);
	}

	@Override
	public List<Food> getFoodsByProtien() {
		return foodRepository.findByAvailableTrueOrderByProteinDesc();
	}

}
