package com.gym.controller;

import com.gym.dto.AppointmentRequest;
import com.gym.dto.AppointmentStatusRequest;
import com.gym.model.Appointment;
import com.gym.service.AppointmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private final AppointmentService appointmentService;

	public AppointmentController(
			AppointmentService appointmentService) {

		this.appointmentService = appointmentService;
	}

	@GetMapping
	public List<Appointment> getAllAppointments(
			@RequestParam(required = false)
			LocalDate date) {

		if (date != null) {
			return appointmentService
					.getAppointmentsByDate(date);
		}

		return appointmentService.getAllAppointments();
	}

	@GetMapping("/{id}")
	public Appointment getAppointmentById(
			@PathVariable Long id) {

		return appointmentService.getAppointmentById(id);
	}

	@GetMapping("/member/{memberId}")
	public List<Appointment> getMemberAppointments(
			@PathVariable Long memberId) {

		return appointmentService
				.getAppointmentsByMember(memberId);
	}

	@GetMapping("/trainer/{trainerId}")
	public List<Appointment> getTrainerAppointments(
			@PathVariable Long trainerId) {

		return appointmentService
				.getAppointmentsByTrainer(trainerId);
	}

	@PostMapping
	public ResponseEntity<Appointment> createAppointment(
			@Valid @RequestBody AppointmentRequest request) {

		Appointment created =
				appointmentService
					.createAppointment(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(created);
	}

	@PutMapping("/{id}")
	public Appointment updateAppointment(
			@PathVariable Long id,
			@Valid @RequestBody AppointmentRequest request) {

		return appointmentService
				.updateAppointment(id, request);
	}

	@PatchMapping("/{id}/status")
	public Appointment updateAppointmentStatus(
			@PathVariable Long id,
			@Valid @RequestBody
			AppointmentStatusRequest request) {

		return appointmentService.updateStatus(
				id,
				request.getStatus());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(
			@PathVariable Long id) {

		appointmentService.deleteAppointment(id);

		return ResponseEntity.noContent().build();
	}
}