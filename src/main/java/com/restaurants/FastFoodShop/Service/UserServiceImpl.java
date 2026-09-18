package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (id != null) {
            userRepository.deleteById(id.intValue());
        }
    }

    // Fixed: Wrapped return value in Optional.ofNullable to match Optional<User> signature
    @Override
    public Optional<User> getUserById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id.intValue());
    }

    @Override
    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        return userRepository.save(user);
    }

    // Fixed: Called existsByUserName (capital 'N') to match UserRepository declaration
    @Override
    public boolean existByUserName(String username) {
        if (username == null) {
            return false;
        }
        return userRepository.existsByUserName(username);
    }

    @Override
    public boolean existByEmail(String email) {
        if (email == null) {
            return false;
        }
        return userRepository.existsByEmail(email);
    }

    // Fixed: Wrapped return value in Optional<User> to match UserService signature
    @Override
    public Optional<User> getByUserName(String username) {
        if (username == null) {
            return Optional.empty();
        }
        return userRepository.findByUserName(username);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        Optional<User> userOpt = getByUserName(username);
        if (userOpt.isPresent() && password.equals(userOpt.get().getPassword())) {
            return userOpt.get();
        }
        return null;
    }
}