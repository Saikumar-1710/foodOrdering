package com.restaurants.FastFoodShop.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	//find Based on RoleName
	
	Optional<Role> findByRoleName(String roleName);
	boolean existByRoleName(String roleName);
}
