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
}