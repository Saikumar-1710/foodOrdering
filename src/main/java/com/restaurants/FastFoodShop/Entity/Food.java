package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;
    private boolean available;

    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private double fiber;
    private double magnesium;

    public Food() {}

    // Primary ID
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Core attributes
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Nutritional Info
    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public double getCarbs() { return carbs; }
    public void setCarbs(double carbs) { this.carbs = carbs; }
    
    // Alias getter for CartServiceImpl compatibility
    public double getCarbohydrates() { return carbs; }

    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }
    
    // Alias getter for CartServiceImpl compatibility
    public double getFats() { return fat; }

    public double getFiber() { return fiber; }
    public void setFiber(double fiber) { this.fiber = fiber; }

    public double getMagnesium() { return magnesium; }
    public void setMagnesium(double magnesium) { this.magnesium = magnesium; }
}