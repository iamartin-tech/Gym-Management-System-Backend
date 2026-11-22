package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="administrators")

public class Administrator extends User {
	
	@NotBlank(message = "Administrator role is required")
	private String role;
	
	public Administrator() {
		
	}
	
	public Administrator(String firstName,
			String lastName,
			String email,
			String password,
			String phoneNumber,
			String role) {
		
		super(firstName, lastName, email, password, phoneNumber);
		
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		
		this.role = role;
	}
	

}
