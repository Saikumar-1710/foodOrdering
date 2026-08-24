package com.restaurants.FastFoodShop.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String name;
	@Column(length=1000)
	private String description;
	private Double price;
	private String category;
	private String imageUrl;
	private boolean available = true;
	
	
	//nutritions per serving
	
	private Double protein = 0.0;
	private Double calories= 0.0;
	private Double carbohydrates = 0.0;
	private Double fats=0.0;
	private Double fiber=0.0;
	private Double magnesium=0.0;
	
	//creating list for CustomizationOptions
	
	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomizationOption> customizationOption = new ArrayList<>();
	
	
}
