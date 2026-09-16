package com.restaurants.FastFoodShop.Controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurants.FastFoodShop.Entity.Attendance;
import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.AttendanceService;
import com.restaurants.FastFoodShop.Service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StaffController {

    private final OrderService orderService;
    private final AttendanceService attendanceService;

    public StaffController(OrderService orderService,
                           AttendanceService attendanceService) {

        this.orderService = orderService;
        this.attendanceService = attendanceService;
    }

    // =====================================================
    // STAFF DASHBOARD
    // =====================================================

    @GetMapping("/staff/dashboard")
    public String staffDashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole() == null ||
                !"STAFF".equalsIgnoreCase(
                        user.getRole().getRoleName())) {

            return "redirect:/login";
        }

        Integer staffId = user.getId();

        // Get staff orders
        List<Order> orders =
                orderService.getOrdersByStaff(staffId);

        // Get staff attendance
        List<Attendance> attendanceList =
                attendanceService.getAttendanceByStaff(staffId);

        LocalDate today = LocalDate.now();

        // =====================================================
        // TODAY'S ORDERS
        // =====================================================

        long todayOrders = orders.stream()
                .filter(order ->
                        order.getOrderDate() != null &&
                        order.getOrderDate()
                                .toLocalDate()
                                .equals(today))
                .count();

        // =====================================================
        // TOTAL ORDERS
        // =====================================================

        long totalOrders = orders.size();

        // =====================================================
        // COMPLETED ORDERS
        // =====================================================

        long completedOrders = orders.stream()
                .filter(order ->
                        order.getStatus() != null &&
                        "COMPLETED".equalsIgnoreCase(
                                order.getStatus()))
                .count();
        // =====================================================
        // TODAY'S WORKING HOURS
        // =====================================================

        double todayWorkingHours = 0.0;

        Attendance todayAttendance =
                attendanceService
                        .getAttendanceByStaffAndDate(
                                staffId,
                                today)
                        .orElse(null);

        if (todayAttendance != null) {

            if (todayAttendance.getWorkingHours() != null) {

                todayWorkingHours =
                        todayAttendance.getWorkingHours();

            } else if (todayAttendance.getLoginTime() != null) {

                LocalDateTime loginTime =
                        todayAttendance.getLoginTime();

                LocalDateTime currentTime =
                        LocalDateTime.now();

                Duration duration =
                        Duration.between(
                                loginTime,
                                currentTime);

                todayWorkingHours =
                        duration.toMinutes() / 60.0;

                todayWorkingHours =
                        Math.round(
                                todayWorkingHours * 100.0)
                                / 100.0;
            }
        }

        // =====================================================
        // SEND DATA TO THYMELEAF
        // =====================================================

        model.addAttribute("staff", user);
        model.addAttribute("orders", orders);
        model.addAttribute("attendanceList", attendanceList);

        model.addAttribute("todayOrders", todayOrders);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("todayWorkingHours", todayWorkingHours);

        return "staff/dashboard";
    }

    // =====================================================
    // UPDATE ORDER STATUS
    // =====================================================

    @PostMapping("/staff/order/update-status")
    public String updateOrderStatus(
            @RequestParam Integer orderId,
            @RequestParam String status,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole() == null ||
                !"STAFF".equalsIgnoreCase(
                        user.getRole().getRoleName())) {

            return "redirect:/login";
        }

        Order order =
                orderService
                        .getOrderById(orderId)
                        .orElse(null);

        if (order == null) {
            return "redirect:/staff/dashboard";
        }

        // Staff can update only their own order
        if (order.getStaff() == null ||
                !order.getStaff()
                        .getId()
                        .equals(user.getId())) {

            return "redirect:/staff/dashboard";
        }

        // Allowed order statuses
        if ("PENDING".equalsIgnoreCase(status) ||
                "PREPARING".equalsIgnoreCase(status) ||
                "READY".equalsIgnoreCase(status) ||
                "COMPLETED".equalsIgnoreCase(status) ||
                "CANCELLED".equalsIgnoreCase(status)) {

            order.setStatus(
                    status.toUpperCase());

            orderService.updateOrder(order);
        }

        return "redirect:/staff/dashboard";
    }

    // =====================================================
    // UPDATE PAYMENT STATUS
    // =====================================================

    @PostMapping("/staff/order/update-payment")
    public String updatePaymentStatus(
            @RequestParam Integer orderId,
            @RequestParam String paymentStatus,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        // Check login
        if (user == null) {
            return "redirect:/login";
        }

        // Allow only STAFF
        if (user.getRole() == null ||
                !"STAFF".equalsIgnoreCase(
                        user.getRole().getRoleName())) {

            return "redirect:/login";
        }

        // Find order
        Order order =
                orderService
                        .getOrderById(orderId)
                        .orElse(null);

        if (order == null) {
            return "redirect:/staff/dashboard";
        }

        // =====================================================
        // SECURITY CHECK
        // Staff can update ONLY their own orders
        // =====================================================

        if (order.getStaff() == null ||
                !order.getStaff()
                        .getId()
                        .equals(user.getId())) {

            return "redirect:/staff/dashboard";
        }

        // =====================================================
        // ALLOWED PAYMENT STATUSES
        // =====================================================

        if ("PENDING".equalsIgnoreCase(paymentStatus) ||
                "PAID".equalsIgnoreCase(paymentStatus) ||
                "FAILED".equalsIgnoreCase(paymentStatus)) {

            order.setPaymentStatus(
                    paymentStatus.toUpperCase());

            orderService.updateOrder(order);
        }

        return "redirect:/staff/dashboard";
    }
}