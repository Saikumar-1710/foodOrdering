package com.restaurants.FastFoodShop.Repository;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizationOptionRepository extends JpaRepository<CustomizationOption, Long> {

    // Derived Query Method for Spring Data JPA
    List<CustomizationOption> findByFoodId(Long foodId);
    
    // Overload for Integer foodId if your entity/controller uses Integer
    List<CustomizationOption> findByFoodId(Integer foodId);
}