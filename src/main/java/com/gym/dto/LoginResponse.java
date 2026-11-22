package com.gym.dto;

public class LoginResponse {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String role;
	
	public LoginResponse() {
	
	}
	public LoginResponse(
			Long userId,
			String firstName,
			String lastName,
			String email,
			String phoneNumber,
			String role) {
			
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
		
	public Long getUserId() {
		return userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getRole() {
		return role;
	}

}
