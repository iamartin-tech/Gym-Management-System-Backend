package com.gym.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.Administrator;
import com.gym.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	private final AdministratorRepository administratorRepository;
	private final PasswordEncoder passwordEncoder;

	public AdministratorService(
			AdministratorRepository administratorRepository,
			PasswordEncoder passwordEncoder) {

		this.administratorRepository =
				administratorRepository;

		this.passwordEncoder = passwordEncoder;
	}

	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

	public Administrator getAdministratorById(Long id) {

		return administratorRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Administrator not found with ID: "
							+ id));
	}

	public Administrator createAdministrator(
			Administrator administrator) {

		if (administratorRepository.existsByEmail(
				administrator.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		if (administrator.getPassword() == null
				|| administrator.getPassword().isBlank()) {

			throw new IllegalArgumentException(
					"Password is required");
		}

		administrator.setPassword(
				passwordEncoder.encode(
						administrator.getPassword()));

		/*
		 * Set a default role if none was provided.
		 */
		if (administrator.getRole() == null
				|| administrator.getRole().isBlank()) {

			administrator.setRole("ADMINISTRATOR");
		}

		return administratorRepository.save(administrator);
	}

	public Administrator updateAdministrator(
			Long id,
			Administrator updatedAdministrator) {

		Administrator existing =
				getAdministratorById(id);

		if (!existing.getEmail().equalsIgnoreCase(
				updatedAdministrator.getEmail())
				&& administratorRepository.existsByEmail(
						updatedAdministrator.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		existing.setFirstName(
				updatedAdministrator.getFirstName());

		existing.setLastName(
				updatedAdministrator.getLastName());

		existing.setEmail(
				updatedAdministrator.getEmail());

		existing.setPhoneNumber(
				updatedAdministrator.getPhoneNumber());

		existing.setRole(
				updatedAdministrator.getRole());

		/*
		 * Do not update the password here.
		 * Password changes should use:
		 *
		 * PUT /users/{id}/password
		 */

		return administratorRepository.save(existing);
	}

	public void deleteAdministrator(Long id) {

		Administrator administrator =
				getAdministratorById(id);

		administratorRepository.delete(administrator);
	}
}