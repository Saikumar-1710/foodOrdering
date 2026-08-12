package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean existByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User login(String userName, String password) {
		Optional<User> optionalUser = userRepository.findByUserName(userName);	
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			//validate password
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
