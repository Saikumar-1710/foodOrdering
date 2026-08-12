package com.restaurants.FastFoodShop.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserName(String userName);
	Optional<User> findByEmail(String email);
	Optional<User> findByPhone(String mobile);
	
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
	boolean existsByPhone(String mobile);
	
	List<User> findByEnabled(boolean enabled);
	
}
