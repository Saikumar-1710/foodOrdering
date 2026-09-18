package com.restaurants.FastFoodShop.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class OrderController {

    // Serve HTML Pages

    @GetMapping("/order-success")
    public String showOrderSuccess() {
        return "customer/order-success";
    }

    @GetMapping("/orders")
    public String showOrderHistory() {
        return "customer/orders";
    }

    // API Endpoints for Task 6 AJAX Operations

    @PostMapping("/api/orders/place")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> orderPayload) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Generate a unique Order ID for Task 6
            String orderId = "ORD-" + System.currentTimeMillis() % 100000;
            
            response.put("success", true);
            response.put("message", "Order placed successfully!");
            response.put("orderId", orderId);
            response.put("timestamp", new Date().toString());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to place order: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}