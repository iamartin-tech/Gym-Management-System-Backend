package com.gym.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRequest {

	@NotNull(message = "Attendance date is required")
	@PastOrPresent(
			message = "Attendance date cannot be in the future")
	private LocalDate attendanceDate;

	private LocalTime checkInTime;
	private LocalTime checkOutTime;

	@NotNull(message = "Member ID is required")
	private Long memberId;

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(
			LocalDate attendanceDate) {

		this.attendanceDate = attendanceDate;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(
			LocalTime checkInTime) {

		this.checkInTime = checkInTime;
	}

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(
			LocalTime checkOutTime) {

		this.checkOutTime = checkOutTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}