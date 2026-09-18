package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @PostMapping("/verify")
    public ResponseEntity<String> verifyStaff(@RequestBody User user) {
        if (user != null && "ROLE_STAFF".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.ok("Staff Access Granted");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }
}