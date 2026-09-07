package com.foodordering.admin.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final JdbcTemplate jdbcTemplate;

    public DashboardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long getTotalFoods() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM foods", Long.class);
    }

    public long getTotalCustomizations() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM customization_option", Long.class);
    }

    public long getTotalOrders() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM orders", Long.class);
    }

    public long getTotalCustomers() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users u " +
                "JOIN role r ON u.role_id = r.id " +
                "WHERE UPPER(r.role_name) = 'CUSTOMER'",
                Long.class);
    }

    public long getTotalStaff() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users u " +
                "JOIN role r ON u.role_id = r.id " +
                "WHERE UPPER(r.role_name) = 'STAFF'",
                Long.class);
    }
}