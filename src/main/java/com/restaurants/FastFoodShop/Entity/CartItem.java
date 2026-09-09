package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Double unitPrice;

    private Double calories;

    private Double protein;

    private Double carbohydrates;

    private Double fats;

    private Double fiber;

    private Double magnesium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @OneToMany(
            mappedBy = "cartItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartItemOption> selectedOptions = new ArrayList<>();
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    public void setSelectedOptions(List<CartItemOption> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public Integer getId() {
        return id;
    }

}