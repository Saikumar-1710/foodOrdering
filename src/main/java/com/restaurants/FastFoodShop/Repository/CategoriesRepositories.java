package com.restaurants.FastFoodShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Categories;

@Repository
public interface CategoriesRepositories  extends JpaRepository<Categories, Integer>{

}
