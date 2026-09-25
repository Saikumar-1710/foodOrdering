package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.User;

import java.util.List;

public interface CartService {

    Cart getCart(User user);

    void addToCart(User user,Integer foodId,List<Integer> optionIds);

    void increaseQuantity(User user, Integer cartItemId);

    void decreaseQuantity(User user, Integer cartItemId);

    void removeCartItem(User user,Integer cartItemId);

    void clearCart(User user);
}