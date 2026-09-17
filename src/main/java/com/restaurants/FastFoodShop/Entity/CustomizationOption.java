package com.restaurants.FastFoodShop.Entity;

import com.restaurants.FastFoodShop.Enum.CustomizationType;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customization_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomizationType type;

    @Column(name = "price_adjustment")
    private Double priceAdjustment = 0.0;

    private Double calories = 0.0;

    private Double protein = 0.0;

    private Double carbohydrates = 0.0;

    private Double fats = 0.0;

    private Double fiber = 0.0;

    private Double magnesium = 0.0;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;
    
    // Explicit getter
    public Food getFood() {
        return food;
    }

    // Explicit setter
    public void setFood(Food food) {
        this.food = food;
    }

    // Explicit getter for id
    public Integer getId() {
        return id;
    }

    // Explicit getter/setter for priceAdjustment
  

    public void setPriceAdjustment(Double priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
    }

    
    public Double getPriceAdjustment() {
        return priceAdjustment;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getFats() {
        return fats;
    }

    public Double getFiber() {
        return fiber;
    }

    public Double getMagnesium() {
        return magnesium;
    }

	
}