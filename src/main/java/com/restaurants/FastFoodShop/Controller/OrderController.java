package com.restaurants.FastFoodShop.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.OrderItem;
import com.restaurants.FastFoodShop.Entity.Payment;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.UserRepository;
import com.restaurants.FastFoodShop.Service.CartService;
import com.restaurants.FastFoodShop.Service.OrderService;
import com.restaurants.FastFoodShop.Service.PaymentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final UserRepository userRepository;

    public OrderController(
            CartService cartService,
            OrderService orderService,
            PaymentService paymentService,
            UserRepository userRepository) {

        this.cartService = cartService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.userRepository = userRepository;
    }

    @GetMapping("/checkout")
    public String checkout(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole() == null ||
                !"CUSTOMER".equalsIgnoreCase(
                        user.getRole().getRoleName())) {

            return "redirect:/login";
        }

        Cart cart = cartService.getCart(user);

        if (cart == null ||
                cart.getItems() == null ||
                cart.getItems().isEmpty()) {

            return "redirect:/customer/cart";
        }

        double totalAmount = cart.getItems()
                .stream()
                .mapToDouble(item ->
                        item.getUnitPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);

        return "customer/checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(
            @RequestParam String paymentMethod,
            HttpSession session,
            Model model) {

        User customer =
                (User) session.getAttribute("loggedUser");

        if (customer == null) {
            return "redirect:/login";
        }

        if (customer.getRole() == null ||
                !"CUSTOMER".equalsIgnoreCase(
                        customer.getRole().getRoleName())) {

            return "redirect:/login";
        }

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            return "redirect:/customer/checkout";
        }

        String method =
                paymentMethod.trim().toUpperCase();

        if (!method.equals("CASH") &&
                !method.equals("CARD") &&
                !method.equals("ONLINE")) {

            return "redirect:/customer/checkout";
        }

        Cart cart = cartService.getCart(customer);

        if (cart == null ||
                cart.getItems() == null ||
                cart.getItems().isEmpty()) {

            return "redirect:/customer/cart";
        }

        double totalAmount = cart.getItems()
                .stream()
                .mapToDouble(item ->
                        item.getUnitPrice() * item.getQuantity())
                .sum();

        List<User> staffUsers =
                userRepository.findByEnabledAndRoleRoleName(
                        true,
                        "STAFF");

        System.out.println(
                "STAFF USERS FOUND: " + staffUsers.size());

        User assignedStaff = null;

        if (!staffUsers.isEmpty()) {
            assignedStaff = staffUsers.get(0);
        }

        Order order = new Order();

        order.setCustomer(customer);
        order.setStaff(assignedStaff);
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        order.setPaymentMethod(method);
        order.setPaymentStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        Map<Integer, OrderItem> itemMap =
                new LinkedHashMap<>();

        cart.getItems().forEach(cartItem -> {

            if (cartItem.getFood() == null) {
                return;
            }

            Integer foodId =
                    cartItem.getFood().getId();

            if (itemMap.containsKey(foodId)) {

                OrderItem existingItem =
                        itemMap.get(foodId);

                int oldQuantity =
                        existingItem.getQuantity() != null
                                ? existingItem.getQuantity()
                                : 0;

                int newQuantity =
                        cartItem.getQuantity() != null
                                ? cartItem.getQuantity()
                                : 0;

                existingItem.setQuantity(
                        oldQuantity + newQuantity);

            } else {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrder(order);
                orderItem.setFood(cartItem.getFood());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setUnitPrice(cartItem.getUnitPrice());

                itemMap.put(foodId, orderItem);
            }
        });

        List<OrderItem> orderItems =
                new ArrayList<>(itemMap.values());

        order.setItems(orderItems);

        Order savedOrder =
                orderService.saveOrder(order);

        Payment payment =
                new Payment();

        payment.setOrder(savedOrder);
        payment.setPaymentMethod(method);
        payment.setPaymentStatus("PENDING");
        payment.setAmount(totalAmount);
        payment.setPaymentDate(LocalDateTime.now());

        paymentService.savePayment(payment);

        cartService.clearCart(customer);

        return "redirect:/customer/dashboard";
    }
}