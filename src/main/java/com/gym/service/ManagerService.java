package com.gym.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.Manager;
import com.gym.repository.ManagerRepository;

@Service
@Transactional
public class ManagerService {

	private final ManagerRepository managerRepository;
	private final PasswordEncoder passwordEncoder;

	public ManagerService(
			ManagerRepository managerRepository,
			PasswordEncoder passwordEncoder) {

		this.managerRepository = managerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Manager> getAllManagers() {
		return managerRepository.findAll();
	}

	public Manager getManagerById(Long id) {

		return managerRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Manager not found with ID: " + id));
	}

	public Manager createManager(Manager manager) {

		if (managerRepository.existsByEmail(
				manager.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		if (manager.getPassword() == null
				|| manager.getPassword().isBlank()) {

			throw new IllegalArgumentException(
					"Password is required");
		}

		manager.setPassword(
				passwordEncoder.encode(manager.getPassword()));

		return managerRepository.save(manager);
	}

	public Manager updateManager(
			Long id,
			Manager updatedManager) {

		Manager existing = getManagerById(id);

		if (!existing.getEmail()
				.equalsIgnoreCase(updatedManager.getEmail())
				&& managerRepository.existsByEmail(
						updatedManager.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		existing.setFirstName(
				updatedManager.getFirstName());

		existing.setLastName(
				updatedManager.getLastName());

		existing.setEmail(
				updatedManager.getEmail());

		existing.setPhoneNumber(
				updatedManager.getPhoneNumber());

		existing.setDepartment(
				updatedManager.getDepartment());

		/*
		 * Do not update the password here.
		 * Password changes should use:
		 *
		 * PUT /users/{id}/password
		 */

		return managerRepository.save(existing);
	}

	public void deleteManager(Long id) {

		Manager manager = getManagerById(id);

		managerRepository.delete(manager);
	}
}