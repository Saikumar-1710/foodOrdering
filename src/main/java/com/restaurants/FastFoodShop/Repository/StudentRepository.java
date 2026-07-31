package com.restaurants.FastFoodShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
