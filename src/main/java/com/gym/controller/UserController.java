package com.gym.controller;

import com.gym.dto.ChangeEmailRequest;
import com.gym.dto.ChangePasswordRequest;
import com.gym.dto.LoginRequest;
import com.gym.dto.LoginResponse;
import com.gym.dto.UpdateProfileRequest;
import com.gym.model.User;
import com.gym.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/login")
	public LoginResponse login(
			@Valid @RequestBody LoginRequest request) {

		return userService.login(
				request.getEmail(),
				request.getPassword());
	}

	@PutMapping("/{id}/profile")
	public User updateProfile(
			@PathVariable Long id,
			@Valid @RequestBody UpdateProfileRequest request) {

		return userService.updateProfile(id, request);
	}

	@PutMapping("/{id}/password")
	public User changePassword(
			@PathVariable Long id,
			@Valid @RequestBody ChangePasswordRequest request) {

		return userService.changePassword(
				id,
				request.getCurrentPassword(),
				request.getNewPassword());
	}

	@PutMapping("/{id}/email")
	public User changeEmail(
			@PathVariable Long id,
			@Valid @RequestBody ChangeEmailRequest request) {

		return userService.changeEmail(
				id,
				request.getPassword(),
				request.getNewEmail());
	}
}
