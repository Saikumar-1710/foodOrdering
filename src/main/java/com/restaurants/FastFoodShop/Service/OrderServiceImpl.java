package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByStaff(Integer staffId) {
        return orderRepository.findByStaffId(staffId);
    }

    @Override
    public List<Order> getOrdersByStaffAndStatus(Integer staffId, String status) {
        return orderRepository.findByStaffIdAndStatus(staffId, status);
    }
}