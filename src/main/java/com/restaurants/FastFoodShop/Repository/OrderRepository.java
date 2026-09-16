package com.restaurants.FastFoodShop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByStaffId(Integer staffId);

    List<Order> findByStaffIdAndStatus(Integer staffId, String status);
}