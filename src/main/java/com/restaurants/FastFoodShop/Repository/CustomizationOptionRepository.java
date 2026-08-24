package com.restaurants.FastFoodShop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;

@Repository
public interface CustomizationOptionRepository
        extends JpaRepository<CustomizationOption, Integer> {

    List<CustomizationOption> findByFood_IdAndAvailableTrue(
            Integer foodId
    );
}