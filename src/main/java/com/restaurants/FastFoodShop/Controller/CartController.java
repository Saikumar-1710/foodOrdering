package com.restaurants.FastFoodShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CartController {

    @GetMapping("/cart")
    public String showCart() {
        return "customer/cart";
    }

    @GetMapping("/checkout")
    public String showCheckout() {
        return "customer/checkout";
    }

    @GetMapping("/orders")
    public String showOrders() {
        return "customer/orders";
    }

    @GetMapping("/nutrition")
    public String showNutritionProfile() {
        return "customer/nutrition-profile";
    }

    @GetMapping("/recommendations")
    public String showRecommendations() {
        return "customer/recommendations";
    }
}