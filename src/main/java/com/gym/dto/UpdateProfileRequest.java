package com.gym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {
	
	@NotBlank(message = "First name is required")
	@Size(max = 50,
		message = "First name cannot exceed 50 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	@Size(max = 50,
		message = "Last name cannot exceed 50 characters")
	private String lastName;
	
	@Pattern(
			regexp = "^$|^[0-9()\\-+ ]{7,20}$",
			message = "Phone number contains invalid characters"
	)
	private String phoneNumber;
	
	public UpdateProfileRequest() {
		
	}
	
	public UpdateProfileRequest(
			String firstName,
			String lastName,
			String phoneNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
