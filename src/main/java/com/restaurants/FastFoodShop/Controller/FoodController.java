package com.restaurants.FastFoodShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.CustomizationOptionService;
import com.restaurants.FastFoodShop.Service.FoodService;

@Controller
@RequestMapping("/customer")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@Autowired
	private CustomizationOptionService customizationService;

	//Http Actions
	
	@GetMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("foods",foodService.getAvaliableFoods());
		return "customer/menu";
	}
	

	@GetMapping("/food/{id}")
	public String customizedFood(@PathVariable Integer id, Model model) {
		Food food= foodService.getFoodById(id).orElseThrow(()-> new RuntimeException("Food Not Found"));
		model.addAttribute("food",food);
		model.addAttribute("options",customizationService.getOptionByFood(id));
		return "customer/customize-food";
	}
	
}
