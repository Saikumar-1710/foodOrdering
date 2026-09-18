package com.restaurants.FastFoodShop.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restaurants.FastFoodShop.Entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByAvailableTrue();

    List<Food> findByCategoryAndAvailableTrue(String category);

    List<Food> findByAvailableTrueOrderByProteinDesc();

    List<Food> findByProteinGreaterThanEqual(double minProtein);
}