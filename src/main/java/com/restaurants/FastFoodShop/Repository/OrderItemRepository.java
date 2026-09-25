package com.restaurants.FastFoodShop.Repository;

import com.restaurants.FastFoodShop.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderOrderDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<OrderItem> findByOrderOrderDateBetweenAndOrderStatus(
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String status
    );
}