package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.User;

public interface UserService {

	User saveUser(User user);
	User updateUser(User user);
	void deleteUser(Long id);
	
	Optional<User> getUserById(Long id);
	Optional<User> getByUserName(String userName);
	List<User> getAllUsers();
	boolean existByUserName(String userName);
	boolean existByEmail(String email);
	
	User login(String userName, String password);
}
