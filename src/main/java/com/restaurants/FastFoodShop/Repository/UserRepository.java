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
	Optional<User> findByUserNameAndPassword(String username, String password);
	
	boolean existByUserName(String username);
	boolean existByEmail(String email);
	boolean existByPhone(String mobile);
	
	List<User> findByEnabled(boolean enabled);
	
}
