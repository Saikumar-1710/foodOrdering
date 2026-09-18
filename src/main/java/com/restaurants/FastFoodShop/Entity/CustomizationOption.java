package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customization_options")
public class CustomizationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double priceAdjustment;
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    // Nutritional adjustment attributes
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fats;
    private double fiber;
    private double magnesium;

    public CustomizationOption() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPriceAdjustment() { return priceAdjustment; }
    public void setPriceAdjustment(double priceAdjustment) { this.priceAdjustment = priceAdjustment; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(double carbohydrates) { this.carbohydrates = carbohydrates; }

    public double getFats() { return fats; }
    public void setFats(double fats) { this.fats = fats; }

    public double getFiber() { return fiber; }
    public void setFiber(double fiber) { this.fiber = fiber; }

    public double getMagnesium() { return magnesium; }
    public void setMagnesium(double magnesium) { this.magnesium = magnesium; }
}