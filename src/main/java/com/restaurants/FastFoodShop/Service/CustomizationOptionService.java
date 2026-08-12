package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;

public interface CustomizationOptionService {

	CustomizationOption saveOption(CustomizationOption customizationOption);
    List<CustomizationOption> getOptionByFood(Integer foodId);
	Optional<CustomizationOption> getOptionById(Integer id);
}
