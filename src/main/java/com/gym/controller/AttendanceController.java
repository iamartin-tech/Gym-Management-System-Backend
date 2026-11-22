package com.gym.controller;

import com.gym.dto.AttendanceRequest;
import com.gym.dto.CheckOutRequest;
import com.gym.model.Attendance;
import com.gym.service.AttendanceService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	private final AttendanceService attendanceService;

	public AttendanceController(
			AttendanceService attendanceService) {

		this.attendanceService = attendanceService;
	}

	@GetMapping
	public List<Attendance> getAttendance() {
		return attendanceService.getAllAttendance();
	}

	@GetMapping("/{id}")
	public Attendance getAttendanceById(
			@PathVariable Long id) {

		return attendanceService.getAttendanceById(id);
	}

	@GetMapping("/member/{memberId}")
	public List<Attendance> getMemberAttendance(
			@PathVariable Long memberId) {

		return attendanceService
				.getMemberAttendance(memberId);
	}

	@PostMapping
	public ResponseEntity<Attendance> createAttendance(
			@Valid @RequestBody AttendanceRequest request) {

		Attendance created =
				attendanceService.createAttendance(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(created);
	}

	@PutMapping("/{id}")
	public Attendance updateAttendance(
			@PathVariable Long id,
			@Valid @RequestBody AttendanceRequest request) {

		return attendanceService
				.updateAttendance(id, request);
	}

	@PatchMapping("/{id}/check-out")
	public Attendance checkOut(
			@PathVariable Long id,
			@Valid @RequestBody CheckOutRequest request) {

		return attendanceService.checkOut(
				id,
				request.getCheckOutTime());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAttendance(
			@PathVariable Long id) {

		attendanceService.deleteAttendance(id);

		return ResponseEntity.noContent().build();
	}
}