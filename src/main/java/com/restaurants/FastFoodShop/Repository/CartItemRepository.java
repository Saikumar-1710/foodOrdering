package com.restaurants.FastFoodShop.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.CartItem;
import com.restaurants.FastFoodShop.Entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Retrieve all cart items belonging to a specific user
    List<CartItem> findByUser(User user);

    // Retrieve all cart items using the user's primary key ID
    List<CartItem> findByUserId(Long userId);

    // Check if a specific food item is already in the user's cart
    Optional<CartItem> findByUserAndFoodItemId(User user, Long foodItemId);

    // Delete all cart items belonging to a specific user (e.g., after order checkout)
    void deleteByUser(User user);

    // Delete all cart items by user ID
    void deleteByUserId(Long userId);
}