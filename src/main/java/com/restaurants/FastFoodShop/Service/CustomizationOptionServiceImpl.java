package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import com.restaurants.FastFoodShop.Repository.CustomizationOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomizationOptionServiceImpl implements CustomizationOptionService {

    @Autowired
    private CustomizationOptionRepository customizationOptionRepository;

    @Override
    public List<CustomizationOption> getOptionByFood(Long foodId) {
        if (foodId == null) {
            return Collections.emptyList();
        }
        return customizationOptionRepository.findByFoodId(foodId);
    }

    @Override
    public List<CustomizationOption> getOptionByFood(Integer foodId) {
        if (foodId == null) {
            return Collections.emptyList();
        }
        return getOptionByFood(foodId.longValue());
    }

    @Override
    public CustomizationOption saveOption(CustomizationOption option) {
        return customizationOptionRepository.save(option);
    }
}