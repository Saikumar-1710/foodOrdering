package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import java.util.List;

public interface CustomizationOptionService {
    List<CustomizationOption> getOptionByFood(Long foodId);
    List<CustomizationOption> getOptionByFood(Integer foodId);
    CustomizationOption saveOption(CustomizationOption option);
}