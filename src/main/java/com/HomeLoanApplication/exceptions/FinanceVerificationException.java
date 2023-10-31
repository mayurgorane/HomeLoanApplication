package com.HomeLoanApplication.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class FinanceVerificationException extends RuntimeException {

	public FinanceVerificationException(String message) {
		super(message);
	}

}
