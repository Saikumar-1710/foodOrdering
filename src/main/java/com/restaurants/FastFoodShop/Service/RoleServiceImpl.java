package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Role;
import com.restaurants.FastFoodShop.Repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleById(Integer id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> getRoleByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
