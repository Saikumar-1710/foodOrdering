package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.Role;

public interface RoleService {

	Role saveRole(Role role);
	List<Role> getAllRoles();
	
	Optional<Role> getRoleById(Integer id);
	Optional<Role> getRoleByName(String roleName);
}
