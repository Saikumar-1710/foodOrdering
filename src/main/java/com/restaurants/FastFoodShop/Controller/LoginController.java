package com.restaurants.FastFoodShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService =userService;
	}
	
	//http methods
	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String userName,@RequestParam String password, HttpSession session,Model model) {
		
		//Creating User obj
		User user = userService.login(userName, password);
			if(user == null) {
				model.addAttribute("error", "Invalid UserName or Password");
				return "login";
			}
			//store the loged in user in session
			session.setAttribute("loggedUser", user);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("role", user.getRole().getRoleName());
			
			//based on role Navigating to dashboard.
			String role = user.getRole().getRoleName();
			
			if("ADMIN".equalsIgnoreCase(role)) {
				return "redirect:/admin/dashboard";
			}
			if("STAFF".equalsIgnoreCase(role)) {
				return "redirect:/staff/dashboard";
			}
			if("CUSTOMER".equalsIgnoreCase(role)) {
				return "redirect:/customer/dashboard";
			}
			
			model.addAttribute("error","Role Not Found");

		return "login";
	}
	
	//logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/login";
	}
	
}
