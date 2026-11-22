package com.gym.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.Trainer;
import com.gym.repository.TrainerRepository;

@Service
@Transactional
public class TrainerService {

	private final TrainerRepository trainerRepository;
	private final PasswordEncoder passwordEncoder;

	public TrainerService(
			TrainerRepository trainerRepository,
			PasswordEncoder passwordEncoder) {

		this.trainerRepository = trainerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	public Trainer getTrainerById(Long id) {

		return trainerRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Trainer not found with ID: " + id));
	}

	public Trainer createTrainer(Trainer trainer) {

		if (trainerRepository.existsByEmail(trainer.getEmail())) {
			throw new DuplicateResourceException(
					"Email is already in use");
		}

		if (trainer.getPassword() == null
				|| trainer.getPassword().isBlank()) {

			throw new IllegalArgumentException(
					"Password is required");
		}

		trainer.setPassword(
				passwordEncoder.encode(trainer.getPassword()));

		return trainerRepository.save(trainer);
	}

	public Trainer updateTrainer(
			Long id,
			Trainer updatedTrainer) {

		Trainer existing = getTrainerById(id);

		if (!existing.getEmail()
				.equalsIgnoreCase(updatedTrainer.getEmail())
				&& trainerRepository.existsByEmail(
						updatedTrainer.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		existing.setFirstName(
				updatedTrainer.getFirstName());

		existing.setLastName(
				updatedTrainer.getLastName());

		existing.setEmail(
				updatedTrainer.getEmail());

		existing.setPhoneNumber(
				updatedTrainer.getPhoneNumber());

		existing.setSpecialization(
				updatedTrainer.getSpecialization());

		/*
		 * Do not update the password here.
		 * Password changes should use:
		 *
		 * PUT /users/{id}/password
		 */

		return trainerRepository.save(existing);
	}

	public void deleteTrainer(Long id) {

		Trainer trainer = getTrainerById(id);

		trainerRepository.delete(trainer);
	}
}