package com.gym.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.model.Manager;
import com.gym.service.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	private final ManagerService managerService;

	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}

	@GetMapping
	public List<Manager> getAllManagers() {
		return managerService.getAllManagers();
	}

	@GetMapping("/{id}")
	public Manager getManagerById(@PathVariable Long id) {
		return managerService.getManagerById(id);
	}

	@PostMapping
	public ResponseEntity<Manager> createManager(
			@Valid @RequestBody Manager manager) {

		Manager createdManager =
				managerService.createManager(manager);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdManager);
	}

	@PutMapping("/{id}")
	public Manager updateManager(
			@PathVariable Long id,
			@Valid @RequestBody Manager manager) {

		return managerService.updateManager(id, manager);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteManager(
			@PathVariable Long id) {

		managerService.deleteManager(id);

		return ResponseEntity.noContent().build();
	}
}
