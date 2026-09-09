package com.restaurants.FastFoodShop.Controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurants.FastFoodShop.Entity.Attendance;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Service.AttendanceService;
import com.restaurants.FastFoodShop.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;
    private final AttendanceService attendanceService;

    public LoginController(UserService userService,
                           AttendanceService attendanceService) {

        this.userService = userService;
        this.attendanceService = attendanceService;
    }

    // ==============================
    // HOME
    // ==============================

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // ==============================
    // LOGIN PAGE
    // ==============================

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ==============================
    // LOGIN
    // ==============================

    @PostMapping("/login")
    public String login(
            @RequestParam String userName,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Validate user
        User user = userService.login(userName, password);

        if (user == null) {
            model.addAttribute(
                    "error",
                    "Invalid UserName or Password"
            );

            return "login";
        }

        // Store logged-in user in session
        session.setAttribute("loggedUser", user);
        session.setAttribute("userName", user.getUserName());
        session.setAttribute(
                "role",
                user.getRole().getRoleName()
        );

        // Get user role
        String role = user.getRole().getRoleName();

        // ==============================
        // STAFF LOGIN
        // ==============================

        if ("STAFF".equalsIgnoreCase(role)) {

            LocalDate today = LocalDate.now();

            Attendance attendance =
                    attendanceService
                            .getAttendanceByStaffAndDate(
                                    user.getId(),
                                    today
                            )
                            .orElse(null);

            // Create attendance if it doesn't exist
            if (attendance == null) {

                attendance = new Attendance();

                attendance.setStaff(user);
                attendance.setDate(today);
                attendance.setLoginTime(LocalDateTime.now());
                attendance.setLogoutTime(null);
                attendance.setWorkingHours(null);

                attendanceService.saveAttendance(attendance);

            }

            // If attendance exists but login time is missing
            else if (attendance.getLoginTime() == null) {

                attendance.setLoginTime(LocalDateTime.now());
                attendance.setLogoutTime(null);
                attendance.setWorkingHours(null);

                attendanceService.updateAttendance(attendance);
            }

            return "redirect:/staff/dashboard";
        }

        // ==============================
        // ADMIN LOGIN
        // ==============================

        if ("ADMIN".equalsIgnoreCase(role)) {

            return "redirect:/admin/dashboard";
        }

        // ==============================
        // CUSTOMER LOGIN
        // ==============================

        if ("CUSTOMER".equalsIgnoreCase(role)) {

            return "redirect:/customer/dashboard";
        }

        // ==============================
        // INVALID ROLE
        // ==============================

        model.addAttribute(
                "error",
                "Role Not Found"
        );

        return "login";
    }

    // ==============================
    // LOGOUT
    // ==============================

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        // ==============================
        // STAFF LOGOUT / ATTENDANCE
        // ==============================

        if (user != null &&
            user.getRole() != null &&
            "STAFF".equalsIgnoreCase(
                    user.getRole().getRoleName())) {

            LocalDate today = LocalDate.now();

            Attendance attendance =
                    attendanceService
                            .getAttendanceByStaffAndDate(
                                    user.getId(),
                                    today
                            )
                            .orElse(null);

            if (attendance != null &&
                attendance.getLoginTime() != null) {

                LocalDateTime logoutTime =
                        LocalDateTime.now();

                attendance.setLogoutTime(logoutTime);

                Duration duration =
                        Duration.between(
                                attendance.getLoginTime(),
                                logoutTime
                        );

                double hours =
                        duration.toMinutes() / 60.0;

                attendance.setWorkingHours(
                        Math.round(hours * 100.0) / 100.0
                );

                attendanceService.updateAttendance(
                        attendance
                );
            }
        }

        // Destroy session
        session.invalidate();

        return "redirect:/login";
    }
    @GetMapping("/guest")
    public String continueAsGuest(HttpSession session) {

        // Mark the current user as guest
        session.setAttribute("isGuest", true);

        // Remove any logged-in user information
        session.removeAttribute("loggedUser");
        session.removeAttribute("userName");
        session.removeAttribute("role");

        // Go to customer dashboard
        return "redirect:/customer/dashboard";
    }
}