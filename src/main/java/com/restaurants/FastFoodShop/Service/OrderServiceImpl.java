package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.CartItem;
import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.OrderItem;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.OrderItemRepository;
import com.restaurants.FastFoodShop.Repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartService cartService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartService cartService) {

        this.orderRepository = orderRepository;

        this.orderItemRepository = orderItemRepository;

        this.cartService = cartService;
    }

    @Override
    @Transactional
    public Order placeOrder(User user) {

        Cart cart = cartService.getCart(user);

        if (cart.getItems().isEmpty()) {

            throw new RuntimeException(
                    "Cart is empty"
            );
        }

        double totalAmount = 0.0;

        for (CartItem item : cart.getItems()) {

            totalAmount +=
                    item.getUnitPrice()
                            * item.getQuantity();
        }

        Order order = new Order();

        order.setUser(user);

        order.setOrderDate(
                java.time.LocalDateTime.now()
        );

        order.setTotalAmount(totalAmount);

        order.setStatus("COMPLETED");

        order = orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(order);

            orderItem.setFood(
                    cartItem.getFood()
            );

            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            orderItem.setUnitPrice(
                    cartItem.getUnitPrice()
            );

            orderItem.setSubtotal(
                    cartItem.getUnitPrice()
                            * cartItem.getQuantity()
            );

            orderItemRepository.save(orderItem);
        }

        cartService.clearCart(user);

        return order;
    }
}