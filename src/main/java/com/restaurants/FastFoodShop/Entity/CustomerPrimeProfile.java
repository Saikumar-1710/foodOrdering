package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customerprimeprofiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPrimeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "protein_required")
    private Double proteinRequired = 50.0;

    @Column(name = "calories_required")
    private Double caloriesRequired = 50.0;

    @Column(name = "carbs_required")
    private Double carbsRequired = 40.0;

    @Column(name = "fats_required")
    private Double fatsRequired = 20.0;

    @Column(name = "requirement_text", length = 500)
    private String requirementText;
}