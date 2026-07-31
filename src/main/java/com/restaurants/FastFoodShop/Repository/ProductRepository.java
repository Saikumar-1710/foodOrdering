package com.restaurants.FastFoodShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{

}
