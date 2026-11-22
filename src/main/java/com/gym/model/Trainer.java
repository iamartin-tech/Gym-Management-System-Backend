package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="trainers")
public class Trainer extends User {
	
	@NotBlank(message = "Specialization is required")
	@Size(max = 100, message = "Specialization cannot exceed 100 characters")
	private String specialization;
	
	public Trainer() {
		
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public void manageSchedule() {
		
	}
	
	public void viewAppointments() {
		
	}
	
	

}
