package com.HomeLoanApplication.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String message) {
		super(message);
	}

}
