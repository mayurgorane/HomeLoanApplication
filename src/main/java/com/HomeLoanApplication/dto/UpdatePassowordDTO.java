package com.HomeLoanApplication.dto;

public class UpdatePassowordDTO {
	
	String userName;
	String currentPassword;
	String updatedPassword;
	
	
	public UpdatePassowordDTO() {
	}


	public UpdatePassowordDTO(String userName, String currentPassword, String updatedPassword) {
		this.userName = userName;
		this.currentPassword = currentPassword;
		this.updatedPassword = updatedPassword;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCurrentPassword() {
		return currentPassword;
	}


	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}


	public String getUpdatedPassword() {
		return updatedPassword;
	}


	public void setUpdatedPassword(String updatedPassword) {
		this.updatedPassword = updatedPassword;
	}
	
	
	
	
	
}
