package com.gym.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class CheckOutRequest {

	@NotNull(message = "Checkout time is required")
	private LocalTime checkOutTime;

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(
			LocalTime checkOutTime) {

		this.checkOutTime = checkOutTime;
	}
}