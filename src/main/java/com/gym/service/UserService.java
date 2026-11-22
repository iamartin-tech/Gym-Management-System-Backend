package com.gym.service;

import com.gym.dto.LoginResponse;
import com.gym.dto.UpdateProfileRequest;
import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.User;
import com.gym.model.Administrator;
import com.gym.model.Manager;
import com.gym.model.Member;
import com.gym.model.Trainer;
import com.gym.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"User not found with ID: " + id));
	}

	public LoginResponse login(String email, String password) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() ->
					new IllegalArgumentException(
							"Invalid email or password"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException(
					"Invalid email or password");
		}

		return new LoginResponse(
				user.getUserId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoneNumber(),
				determineRole(user));
	}

	public User updateProfile(
			Long id,
			UpdateProfileRequest request) {

		User user = getUserById(id);

		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPhoneNumber(request.getPhoneNumber());

		return userRepository.save(user);
	}

	public User changePassword(
			Long id,
			String currentPassword,
			String newPassword) {

		User user = getUserById(id);

		if (!passwordEncoder.matches(
				currentPassword,
				user.getPassword())) {

			throw new IllegalArgumentException(
					"Current password is incorrect");
		}

		user.setPassword(
				passwordEncoder.encode(newPassword));

		return userRepository.save(user);
	}

	public User changeEmail(
			Long id,
			String password,
			String newEmail) {

		User user = getUserById(id);

		if (!passwordEncoder.matches(
				password,
				user.getPassword())) {

			throw new IllegalArgumentException(
					"Password is incorrect");
		}

		if (userRepository.existsByEmail(newEmail)) {
			throw new DuplicateResourceException(
					"Email is already in use");
		}

		user.setEmail(newEmail);

		return userRepository.save(user);
	}

	private String determineRole(User user) {

		if (user instanceof Member) {
			return "MEMBER";
		}

		if (user instanceof Trainer) {
			return "TRAINER";
		}

		if (user instanceof Administrator) {
			return "ADMINISTRATOR";
		}

		if (user instanceof Manager) {
			return "MANAGER";
		}

		return "USER";
	}
}