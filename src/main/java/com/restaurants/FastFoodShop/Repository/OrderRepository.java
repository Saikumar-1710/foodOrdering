package com.restaurants.FastFoodShop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Fetch all orders placed by a specific user
    List<Order> findByUser(User user);

    // Fetch all orders by User ID
    List<Order> findByUserId(Long userId);

    // Fetch orders by status (e.g., "PENDING", "COMPLETED", "CANCELLED")
    List<Order> findByStatus(String status);
}