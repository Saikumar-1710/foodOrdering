package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.User;

public interface OrderService {

    Order placeOrder(User user);
}