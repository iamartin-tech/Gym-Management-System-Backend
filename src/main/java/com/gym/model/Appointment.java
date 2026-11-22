package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;

	@NotNull(message = "Appointment date is required")
	@FutureOrPresent(
			message = "Appointment date cannot be in the past")
	private LocalDate appointmentDate;

	@NotNull(message = "Appointment time is required")
	private LocalTime appointmentTime;

	@NotNull(message = "Member is required")
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@NotNull(message = "Trainer is required")
	@ManyToOne
	@JoinColumn(name = "trainer_id", nullable = false)
	private Trainer trainer;

	@NotNull(message = "Appointment status is required")
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status =
			AppointmentStatus.SCHEDULED;

	public Appointment() {
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
}
