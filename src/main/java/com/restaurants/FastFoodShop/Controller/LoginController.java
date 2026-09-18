package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        User user = userService.getUserById(loginUser.getId()).orElse(null);
        if (user != null) {
            String role = user.getRole(); // Already a String
            if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
                return ResponseEntity.ok("Admin Login Successful");
            } else if ("ROLE_STAFF".equalsIgnoreCase(role)) {
                return ResponseEntity.ok("Staff Login Successful");
            }
        }
        return ResponseEntity.ok("Customer Login Successful");
    }
}