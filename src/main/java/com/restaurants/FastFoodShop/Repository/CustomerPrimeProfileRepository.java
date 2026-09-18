package com.restaurants.FastFoodShop.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.CustomerPrimeProfile;
import com.restaurants.FastFoodShop.Entity.User;

@Repository
public interface CustomerPrimeProfileRepository extends JpaRepository<CustomerPrimeProfile, Long> {

    // Retrieve a CustomerPrimeProfile associated with a specific User entity
    Optional<CustomerPrimeProfile> findByUser(User user);

    // Retrieve a CustomerPrimeProfile using the User's primary key ID
    Optional<CustomerPrimeProfile> findByUserId(Long userId);

    // Check if a CustomerPrimeProfile exists for a given User ID
    boolean existsByUserId(Long userId);

    // Remove a CustomerPrimeProfile by User ID
    void deleteByUserId(Long userId);
}