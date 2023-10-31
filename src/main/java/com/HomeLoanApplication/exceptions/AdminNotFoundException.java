package com.HomeLoanApplication.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class AdminNotFoundException extends RuntimeException {

	public AdminNotFoundException(String message) {
		super(message);
	}

}
