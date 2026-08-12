package com.restaurants.FastFoodShop.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String fullName;

	@Column(nullable=false,unique= true)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	//@Pattern(regexp = "^[6-9][0-9]{9}$")
	@Column(nullable=false, unique=true, length=10)
	private String phone;
	
	@Column(nullable=false)
	private boolean enabled = true;
	//maintain the relationship with role table
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id", nullable=false)
	private Role role;
	
}
