package com.gym.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.model.Trainer;
import com.gym.service.TrainerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

	private final TrainerService trainerService;

	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	@GetMapping
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainers();
	}

	@GetMapping("/{id}")
	public Trainer getTrainerById(@PathVariable Long id) {
		return trainerService.getTrainerById(id);
	}

	@PostMapping
	public ResponseEntity<Trainer> createTrainer(
			@Valid @RequestBody Trainer trainer) {

		Trainer createdTrainer =
				trainerService.createTrainer(trainer);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdTrainer);
	}

	@PutMapping("/{id}")
	public Trainer updateTrainer(
			@PathVariable Long id,
			@Valid @RequestBody Trainer trainer) {

		return trainerService.updateTrainer(id, trainer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTrainer(
			@PathVariable Long id) {

		trainerService.deleteTrainer(id);

		return ResponseEntity.noContent().build();
	}
}
