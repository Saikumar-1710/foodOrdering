package com.restaurants.FastFoodShop.Repository;

import com.restaurants.FastFoodShop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Integer> {

    List<Order> findByOrderDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );
}