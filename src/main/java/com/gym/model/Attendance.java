package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="attendance")

public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;
	
	@NotNull(message = "Attendance date is required")
	@PastOrPresent(message = "Attendance date cannot be in the future")
	private LocalDate attendanceDate;
	
	private LocalTime checkInTime;
	private LocalTime checkOutTime;
	
	@NotNull(message = "Member is required")
	@ManyToOne
	@JoinColumn(name="member_id", nullable = false)
	private Member member;
	
	public Attendance() {
		
	}
	
	public Long getAttendanceId() {
		return attendanceId;
	}
	
	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}
	
	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	public LocalTime getCheckInTime() {
		return checkInTime;
	}
	
	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	
	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}
	
	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}

}
