package com.restaurants.FastFoodShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurants.FastFoodShop.Entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard(HttpSession session) {
		
		User user = (User) session.getAttribute("loggedUser");
		
		if(user == null) {
			return "redirect:/login";
		}
		
		if(!user.getRole().getRoleName().equalsIgnoreCase("ADMIN")) {
			return "redirect:/login";
		}
		
		return "admin/dashboard";
	}
}
