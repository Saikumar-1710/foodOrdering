package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Repository.CustomerPrimeProfileRepository;
import com.restaurants.FastFoodShop.Entity.CustomerPrimeProfile;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private CustomerPrimeProfileRepository customerPrimeProfileRepository;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getRecommendationsByCustomerId(@PathVariable Long customerId) {
        Optional<CustomerPrimeProfile> recommendation = customerPrimeProfileRepository.findById(customerId);
        
        if (recommendation.isPresent()) {
            return ResponseEntity.ok(recommendation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRecommendations() {
        return ResponseEntity.ok(customerPrimeProfileRepository.findAll());
    }
}