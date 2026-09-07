package com.foodordering.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.admin.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    long countByAvaliableTrue();

    long countByAvaliableFalse();
}