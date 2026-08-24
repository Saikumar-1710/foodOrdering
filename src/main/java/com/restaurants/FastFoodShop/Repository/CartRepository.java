package com.restaurants.FastFoodShop.Repository;

import com.restaurants.FastFoodShop.Entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(Integer userId);
}