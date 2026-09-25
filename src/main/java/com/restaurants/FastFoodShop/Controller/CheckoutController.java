package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.OrderService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CheckoutController {

    private final OrderService orderService;

    public CheckoutController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String checkout(
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        Order order =
                orderService.placeOrder(user);

        model.addAttribute(
                "order",
                order
        );

        return "customer/order-success";
    }
}