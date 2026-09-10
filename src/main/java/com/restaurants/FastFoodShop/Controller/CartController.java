package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.Cart;

import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
//Add food to cart
    @PostMapping("/add")
    public String addToCart(
            @RequestParam Integer foodId,
            @RequestParam(required = false) List<Integer> optionIds,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");
        // // Guest user
        if (user == null) {

            // For now, send guest to login
            // Guest cart will be implemented next
        	 session.setAttribute(
                     "cartMessage",
                     "Please login or create an account to add food to the cart."
             );
        	  return "redirect:/customer/menu";
        }
           /* return "redirect:/login";
        }*/

        cartService.addToCart(
                user,
                foodId,
                optionIds
        );

        return "redirect:/customer/cart";
    }
//view cart
    @GetMapping
    public String cart(
            HttpSession session,
            Model model) {

        User user =
        		
                (User) session.getAttribute("loggedUser");
        
     // Guest user
        if (user == null) {
        	session.setAttribute(
                    "cartMessage",
                    "Please login or create an account to view your cart."
            );
            return "redirect:/customer/menu";
        }
        // ==========================================
        // LOGGED-IN CUSTOMER
        // ==========================================

        Cart cart = cartService.getCart(user);

       /* model.addAttribute(
                "cart",
                cart
        );*/

        // Send Prime status to cart.html
        model.addAttribute(
                "isPrime",
                user.isPrimeUser()
        );

        
        return "customer/cart";
    }
//remove cart item
    @PostMapping("/remove/{id}")
    public String removeItem(
            @PathVariable Integer id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");
        if (user == null) {
        	 session.setAttribute(
                     "cartMessage",
                     "Please login to manage your cart."
             );
            return "redirect:/customer/menu";
        }

        cartService.removeCartItem(user, id);

        return "redirect:/customer/cart";
    }
 // ==============================
    // CLEAR CART
    // ==============================

   @PostMapping("/clear")
    public String clearCart(
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
        	 session.setAttribute(
                     "cartMessage",
                     "Please login to manage your cart."
             );
            return "redirect:/customer/menu";
        }

        cartService.clearCart(user);

        return "redirect:/customer/cart";
    }
}
