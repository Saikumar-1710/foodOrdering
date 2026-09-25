package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import com.restaurants.FastFoodShop.Repository.CustomizationOptionRepository;

@Service
public class CustomizationOptionServiceImpl
        implements CustomizationOptionService {

    @Autowired
    private CustomizationOptionRepository customizationOptionRepository;

    @Override
    public CustomizationOption saveOption(
            CustomizationOption customizationOption) {

        return customizationOptionRepository
                .save(customizationOption);
    }

    @Override
    public List<CustomizationOption> getOptionByFood(Integer foodId) {

        return customizationOptionRepository
                .findByFood_IdAndAvailableTrue(foodId);
    }

    @Override
    public Optional<CustomizationOption> getOptionById(
            Integer id) {

        return customizationOptionRepository
                .findById(id);
    }

    @Override
    public void deleteOption(Integer id) {

        customizationOptionRepository.deleteById(id);
    }

    @Override
    public void increaseStock(Integer id) {

        CustomizationOption option =
                customizationOptionRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Customization Not Found"
                                )
                        );

        option.setStock(option.getStock() + 1);

        customizationOptionRepository.save(option);
    }

    @Override
    public void decreaseStock(Integer id) {

        CustomizationOption option =
                customizationOptionRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Customization Not Found"
                                )
                        );

        if (option.getStock() > 0) {

            option.setStock(option.getStock() - 1);

        }

        customizationOptionRepository.save(option);
    }
}