package com.restaurants.FastFoodShop.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.CategoriesRepositories;
import com.restaurants.FastFoodShop.Repository.CustomizationOptionRepository;
import com.restaurants.FastFoodShop.Repository.FoodRepository;
import com.restaurants.FastFoodShop.Repository.OrderRepository;
import com.restaurants.FastFoodShop.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CategoriesRepositories categoriesRepository;
    private final CustomizationOptionRepository customizationOptionRepository;

    public AdminController(
            FoodRepository foodRepository,
            OrderRepository orderRepository,
            UserRepository userRepository,
            CategoriesRepositories categoriesRepository,
            CustomizationOptionRepository customizationOptionRepository) {

        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.customizationOptionRepository = customizationOptionRepository;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole() == null ||
                !user.getRole().getRoleName().equalsIgnoreCase("ADMIN")) {

            return "redirect:/login";
        }

        // Dashboard statistics

        long totalCategories =
                categoriesRepository.count();

        long totalFoods =
                foodRepository.count();

        long totalUsers =
                userRepository.count();

        long totalCustomizations =
                customizationOptionRepository.count();

        long totalOrders =
                orderRepository.count();

        long totalCustomers =
                userRepository.countByRoleRoleName("CUSTOMER");

        long totalStaff =
                userRepository.countByRoleRoleName("STAFF");

        // Today's orders

        LocalDate today = LocalDate.now();

        LocalDateTime startOfDay =
                today.atStartOfDay();

        LocalDateTime endOfDay =
                today.plusDays(1).atStartOfDay();

        long todaysOrders =
                orderRepository.countByOrderDateBetween(
                        startOfDay,
                        endOfDay
                );

        // Recent orders

        List<Order> recentOrders =
                orderRepository
                        .findTop5ByOrderByOrderDateDesc();

        // Send data to dashboard

        model.addAttribute(
                "totalCategories",
                totalCategories
        );

        model.addAttribute(
                "totalFoods",
                totalFoods
        );

        model.addAttribute(
                "totalUsers",
                totalUsers
        );

        model.addAttribute(
                "totalCustomizations",
                totalCustomizations
        );

        model.addAttribute(
                "totalOrders",
                totalOrders
        );

        model.addAttribute(
                "totalCustomers",
                totalCustomers
        );

        model.addAttribute(
                "totalStaff",
                totalStaff
        );

        model.addAttribute(
                "todaysOrders",
                todaysOrders
        );

        model.addAttribute(
                "recentOrders",
                recentOrders
        );

        return "admin/dashboard";
    }
}