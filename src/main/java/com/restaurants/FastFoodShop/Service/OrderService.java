package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.Order;

public interface OrderService {

    Order saveOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(Integer id);

    Optional<Order> getOrderById(Integer id);

    List<Order> getAllOrders();

    List<Order> getOrdersByStaff(Integer staffId);

    List<Order> getOrdersByStaffAndStatus(Integer staffId, String status);
}