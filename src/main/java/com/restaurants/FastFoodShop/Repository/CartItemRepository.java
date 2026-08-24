package com.restaurants.FastFoodShop.Repository;

import com.restaurants.FastFoodShop.Entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}