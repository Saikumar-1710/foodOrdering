package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.User;

import java.util.List;

public interface CartService {

    Cart getCart(User user);

    void addToCart(
            User user,
            Integer foodId,
            List<Integer> optionIds);

    void removeCartItem(
            User user,
            Integer cartItemId);

    void clearCart(User user);

    CartItemUpdate updateQuantity(
            User user,
            Integer cartItemId,
            int quantity);

    double calculateSubtotal(Cart cart);

    double calculateDiscount(
            User user,
            double subtotal);

    double calculateFinalAmount(
            User user,
            Cart cart);

    record CartItemUpdate(
            int quantity,
            double itemSubtotal,
            double subtotal,
            double discount,
            double finalAmount) {
    }

    void addToCart(User user, Integer foodId, Food food);

}