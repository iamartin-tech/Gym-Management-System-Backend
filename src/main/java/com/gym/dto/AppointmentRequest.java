package com.gym.dto;

import com.gym.model.AppointmentStatus;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {

	@NotNull(message = "Appointment date is required")
	@FutureOrPresent(
			message = "Appointment date cannot be in the past")
	private LocalDate appointmentDate;

	@NotNull(message = "Appointment time is required")
	private LocalTime appointmentTime;

	@NotNull(message = "Member ID is required")
	private Long memberId;

	@NotNull(message = "Trainer ID is required")
	private Long trainerId;

	private AppointmentStatus status;

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(
			LocalDate appointmentDate) {

		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(
			LocalTime appointmentTime) {

		this.appointmentTime = appointmentTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
}