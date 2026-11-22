package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name="memberships")

public class Membership {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long membershipId;
	
	@NotBlank(message = "Membership type is required")
	@Size(max = 50, message = "Membership type cannot exceed 50 characters")
	private String membershipType;
	
	@DecimalMin(
			value = "0.0",
			inclusive = false,
			message = "Price must be greater than zero"
	)
	private double price;
	
	@Min(value = 1,
			message = "Duration must be at least one month")
	private int durationMonths;
	
	@JsonIgnore
	@OneToMany(mappedBy="membership")
	private List<Member> members;
	
	public Membership() {
		
	}
	
	public Membership(String membershipType,
			double price,
			int durationMonths) {
		
		this.membershipType = membershipType;
		this.price = price;
		this.durationMonths = durationMonths;
	}
	
	public Long getMembershipId() {
		
		return membershipId;		
	}
	
	public String getMembershipType() {
		
		return membershipType;
	}
	
	public double getPrice() {
		
		return price;
	}
	
	public int getDurationMonths() {
		
		return durationMonths;
	}
	
	public void setMembershipType(String membershipType) {
		
		this.membershipType = membershipType;
	}
	
	public void setPrice(double price) {
		
		this.price = price;
	}
	
	public void setDurationMonths(int durationMonths) {
		
		this.durationMonths = durationMonths;
	}

}
