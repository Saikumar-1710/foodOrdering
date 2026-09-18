package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/prime")
public class CustomerPrimeProfileController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/high-protein")
    public ResponseEntity<List<Food>> getHighProteinRecommendations(@RequestParam(defaultValue = "30") double minProtein) {
        List<Food> highProteinFoods = foodService.getHighProteinFoods(minProtein);
        return ResponseEntity.ok(highProteinFoods);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> processPrimeOrder(@RequestBody Cart cart) {
        Order order = new Order();
        order.setOrderId("ORD" + new Random().nextInt(90000));
        order.setUserId(cart.getUserId());
        order.setTotalPrice(cart.getTotalPrice() * 0.90); // 10% Prime Discount
        
        return ResponseEntity.ok(order);
    }
}