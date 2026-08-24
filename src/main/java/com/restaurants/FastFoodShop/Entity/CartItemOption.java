package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.Entity;
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
@Table(name="cart_item_options")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemOption {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_item_id",nullable=false)
	private CartItem cartItem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="option_id", nullable=false)
	private CustomizationOption option;
}
