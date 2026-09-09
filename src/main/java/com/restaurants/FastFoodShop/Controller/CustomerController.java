package com.restaurants.FastFoodShop.Controller;


/*import org.springframework.stereotype.Controller;
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
}*/


import java.util.List;



import java.util.Optional;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.FoodService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    private final FoodService foodService;

    public CustomerController(FoodService foodService) {
        this.foodService = foodService;
    }

    // ==============================
    // CUSTOMER DASHBOARD
    // ==============================

    @GetMapping("/customer/dashboard")
    public String customerDashboard(HttpSession session, Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
        	/*model.addAttribute("isGuest", true);
            model.addAttribute("isPrime", false);

            return "customer/dashboard";
        }*/

          return "redirect:/login";
        }
//Logged in user 
        if (user.getRole() == null ||
                !"CUSTOMER".equalsIgnoreCase(
                        user.getRole().getRoleName())) {

        	    return "redirect:/login";
        	}

        model.addAttribute("user", user);
       /* model.addAttribute("isGuest", false);
        model.addAttribute("isPrime", true);*/
        model.addAttribute(
                "isPrime",
                user.isPrimeUser()
        );

        return "customer/dashboard";
    }


    // ==============================
    // GUEST + PRIME FOOD PAGE
    // ==============================

    @GetMapping("/customer/menu")
    public String customerMenu(
            HttpSession session,
            Model model) {

        // Get available foods
        List<Food> foods =
                foodService.getAvaliableFoods();

        model.addAttribute("foods", foods);


        // Check logged-in user
        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {

            // Guest user
            model.addAttribute("isGuest", true);
            model.addAttribute("isPrime", false);

        } else {

            // Logged-in customer
        	if (user.getRole() != null &&
                    "CUSTOMER".equalsIgnoreCase(
                            user.getRole().getRoleName())) {

                
            } else {

                // Non-customer user
                return "redirect:/login";
            }
        	model.addAttribute("user", user);
            model.addAttribute("isGuest", false);
            model.addAttribute("isPrime", user.isPrimeUser());

        }

        return "customer/menu";
    }


    // ==============================
    // FOOD DETAILS
    // ==============================

    @GetMapping("/customer/food-details/{id}")
    public String foodDetails(
            @PathVariable Integer id,
            Model model) {

        Optional<Food> food =
                foodService.getFoodById(id);

        if (food.isEmpty()) {
            return "redirect:/customer/menu";
        }

        model.addAttribute("food", food.get());

        return "customer/food-details";
    }
 // ==============================
 // PRIME USER PAGE
 // ==============================

 @GetMapping("/customer/prime")
 public String primePage(
         HttpSession session,
         Model model) {

     User user =
             (User) session.getAttribute("loggedUser");
//not loggedin 
     if (user == null) {
         return "redirect:/login";
     }
//check customer role
     if (user.getRole() == null ||
             !"CUSTOMER".equalsIgnoreCase(
                     user.getRole().getRoleName())) {

         return "redirect:/login";
     }
//check prime user
     if (!user.isPrimeUser()) {
         return "redirect:/customer/menu";
     }

     model.addAttribute("user", user);
     model.addAttribute("isPrime", user.isPrimeUser());

     return "customer/prime";
 }

}