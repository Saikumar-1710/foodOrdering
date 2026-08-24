package com.restaurants.FastFoodShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurants.FastFoodShop.Entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @GetMapping("/customer/dashboard")
    public String customerDashboard(
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (!user.getRole()
                .getRoleName()
                .equalsIgnoreCase("CUSTOMER")) {

            return "redirect:/login";
        }

        return "customer/dashboard";
    }
}