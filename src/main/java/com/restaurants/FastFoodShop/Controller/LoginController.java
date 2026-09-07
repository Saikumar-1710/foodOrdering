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

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String userName,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(userName, password);

        if (user == null) {
            model.addAttribute("error", "Invalid UserName or Password");
            return "login";
        }

        session.setAttribute("loggedUser", user);
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("role", user.getRole().getRoleName());

        String role = user.getRole().getRoleName();

        if ("STAFF".equalsIgnoreCase(role)) {

            LocalDate today = LocalDate.now();

            Attendance attendance =
                    attendanceService
                            .getAttendanceByStaffAndDate(
                                    user.getId(), today)
                            .orElse(null);

            if (attendance == null) {

                attendance = new Attendance();

                attendance.setStaff(user);
                attendance.setDate(today);
                attendance.setLoginTime(LocalDateTime.now());
                attendance.setLogoutTime(null);
                attendance.setWorkingHours(null);

                attendanceService.saveAttendance(attendance);

            } else if (attendance.getLoginTime() == null) {

                attendance.setLoginTime(LocalDateTime.now());
                attendance.setLogoutTime(null);
                attendance.setWorkingHours(null);

                attendanceService.updateAttendance(attendance);
            }

            return "redirect:/staff/dashboard";
        }

        if ("ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/admin/dashboard";
        }

        if ("CUSTOMER".equalsIgnoreCase(role)) {
            return "redirect:/customer/dashboard";
        }

        model.addAttribute("error", "Role Not Found");

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user != null &&
            user.getRole() != null &&
            "STAFF".equalsIgnoreCase(
                    user.getRole().getRoleName())) {

            LocalDate today = LocalDate.now();

            Attendance attendance =
                    attendanceService
                            .getAttendanceByStaffAndDate(
                                    user.getId(), today)
                            .orElse(null);

            if (attendance != null &&
                attendance.getLoginTime() != null) {

                LocalDateTime logoutTime = LocalDateTime.now();

                attendance.setLogoutTime(logoutTime);

                Duration duration =
                        Duration.between(
                                attendance.getLoginTime(),
                                logoutTime);

                double hours =
                        duration.toMinutes() / 60.0;

                attendance.setWorkingHours(
                        Math.round(hours * 100.0) / 100.0);

                attendanceService.updateAttendance(attendance);
            }
        }

        session.invalidate();

        return "redirect:/login";
    }
}