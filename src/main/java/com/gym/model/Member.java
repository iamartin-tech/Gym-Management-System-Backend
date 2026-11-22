package com.gym.model;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class Member extends User {
	
	@ManyToOne
	@JoinColumn(name="membership_id")
	private Membership membership;
	
	public Member() {	
	}
	
	public Membership getMembership() {
		return membership;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public void register() {
		
	}
	
	public void login() {
		
	}
	
	public void viewMembership() {
		
	}
	
	public void bookAppointment() {
		
	}
	
	public void viewAttendance() {
		
	}
}
