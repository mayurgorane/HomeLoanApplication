package com.HomeLoanApplication.controllers;

public class ResponseInfo {
	
	private String message;
	private String status;
	
	public ResponseInfo()
	{
		
	}
	
	public ResponseInfo(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
