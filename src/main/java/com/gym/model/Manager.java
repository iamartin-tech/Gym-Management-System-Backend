package com.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "managers")
public class Manager extends User {
	
	@NotBlank(message = "Department is required")
	@Size(max = 100, message = "Department cannot exceed 100 characters")
	private String department;
	
	public Manager() {
		
	}
	
	public Manager(
			String firstName,
			String lastName,
			String email,
			String password,
			String phoneNumber,
			String department) {
		
		super(
				firstName,
				lastName,
				email,
				password,
				phoneNumber
		);
		
		this.department = department;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void manageTrainers() {
		
	}
	
	public void manageSchedules() {
		
	}
	
	public void viewReports() {
		
	}

}
