package com.gym.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.model.Administrator;
import com.gym.service.AdministratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

	private final AdministratorService administratorService;

	public AdministratorController(
			AdministratorService administratorService) {

		this.administratorService = administratorService;
	}

	@GetMapping
	public List<Administrator> getAllAdministrators() {
		return administratorService.getAllAdministrators();
	}

	@GetMapping("/{id}")
	public Administrator getAdministratorById(
			@PathVariable Long id) {

		return administratorService.getAdministratorById(id);
	}

	@PostMapping
	public ResponseEntity<Administrator> createAdministrator(
			@Valid @RequestBody Administrator administrator) {

		Administrator createdAdministrator =
				administratorService
					.createAdministrator(administrator);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdAdministrator);
	}

	@PutMapping("/{id}")
	public Administrator updateAdministrator(
			@PathVariable Long id,
			@Valid @RequestBody Administrator administrator) {

		return administratorService
				.updateAdministrator(id, administrator);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdministrator(
			@PathVariable Long id) {

		administratorService.deleteAdministrator(id);

		return ResponseEntity.noContent().build();
	}
}
