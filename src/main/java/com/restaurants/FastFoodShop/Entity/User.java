package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;

    private String role; // e.g., "ROLE_ADMIN", "ROLE_USER", "ROLE_STAFF"

    private boolean isPrime;

    private double dailyProteinTarget;

    public User() {}

    public User(Long id, String userName, String password, String email, String role, boolean isPrime, double dailyProteinTarget) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isPrime = isPrime;
        this.dailyProteinTarget = dailyProteinTarget;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isPrime() { return isPrime; }
    public void setPrime(boolean prime) { this.isPrime = prime; }

    public double getDailyProteinTarget() { return dailyProteinTarget; }
    public void setDailyProteinTarget(double dailyProteinTarget) { this.dailyProteinTarget = dailyProteinTarget; }
}