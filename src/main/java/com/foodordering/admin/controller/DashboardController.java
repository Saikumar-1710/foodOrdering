package com.foodordering.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodordering.admin.service.DashboardService;

@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getDashboardStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalFoods", dashboardService.getTotalFoods());
        stats.put("totalCustomizations", dashboardService.getTotalCustomizations());
        stats.put("totalOrders", dashboardService.getTotalOrders());
        stats.put("totalCustomers", dashboardService.getTotalCustomers());
        stats.put("totalStaff", dashboardService.getTotalStaff());

        return ResponseEntity.ok(stats);
    }
}