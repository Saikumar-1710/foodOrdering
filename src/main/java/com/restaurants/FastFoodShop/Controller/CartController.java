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

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Integer foodId,
            @RequestParam(required = false) List<Integer> optionIds,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        cartService.addToCart(
                user,
                foodId,
                optionIds
        );

        return "redirect:/customer/cart";
    }

    @GetMapping
    public String cart(
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        Cart cart = cartService.getCart(user);

        model.addAttribute("cart", cart);

        return "customer/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeItem(
            @PathVariable Integer id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        cartService.removeCartItem(user, id);

        return "redirect:/customer/cart";
    }
}