package com.HomeLoanApplication.exceptions;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public String handlingMethod(CustomerNotFoundException customerNotFoundException) {
		return customerNotFoundException.getMessage();
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String handlingMethod(UserNotFoundException userNotFoundException) {
		return userNotFoundException.getMessage();
	}

	@ExceptionHandler(AdminNotFoundException.class)
	public String handlingMethod(AdminNotFoundException adminNotFoundException) {
		return adminNotFoundException.getMessage();
	}

	@ExceptionHandler(LandVerificationOfficerNotFoundException.class)
	public String handlingMethod(LandVerificationOfficerNotFoundException landVerificationOfficerNotFoundException) {
		return landVerificationOfficerNotFoundException.getMessage();
	}

	@ExceptionHandler(DocumentNotUploadedException.class)
	public String handlingMethod(DocumentNotUploadedException documentNotUploadedException) {
		return documentNotUploadedException.getMessage();
	}

	@ExceptionHandler(FinanceVerificationOfficerNotFoundException.class)
	public String handlingMethod(
			FinanceVerificationOfficerNotFoundException financeVerificationOfficerNotFoundException) {
		return financeVerificationOfficerNotFoundException.getMessage();
	}

	@ExceptionHandler(AdminVerificationException.class)
	public String handlingMethod(AdminVerificationException adminVerificationException) {
		return adminVerificationException.getMessage();
	}

	@ExceptionHandler(LoanApplicationNotFoundException.class)
	public String LoanApplicationNotFoundException(LoanApplicationNotFoundException loanApplicationNotFoundException) {
		return loanApplicationNotFoundException.getMessage();
	}

	@ExceptionHandler(StatusException.class)
	public String StatusException(StatusException statusException) {
		return statusException.getMessage();
	}

	@ExceptionHandler(LoanAgreementNotFoundException.class)
	public String LoanAgreementNotFoundException(LoanAgreementNotFoundException loanAgreementNotFoundException) {
		return loanAgreementNotFoundException.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlerMethodForException(MethodArgumentNotValidException ex) {

		Map<String, String> errorMessage = new LinkedHashMap<>();
		List<ObjectError> list = ex.getAllErrors();
		list.forEach(obj -> {
			FieldError ferr = (FieldError) obj;
			String fname = ferr.getField();
			String msg = ferr.getDefaultMessage();
			errorMessage.put(fname, msg);
		});
		return errorMessage;
	}

}
