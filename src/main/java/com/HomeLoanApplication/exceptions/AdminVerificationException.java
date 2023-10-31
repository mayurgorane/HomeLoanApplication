package com.HomeLoanApplication.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class AdminVerificationException extends RuntimeException {

	public AdminVerificationException(String message) {
		super(message);
	}

}
