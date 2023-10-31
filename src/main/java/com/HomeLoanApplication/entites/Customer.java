package com.HomeLoanApplication.entites;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorValue(value = "customer")
public class Customer extends User {

	@NotEmpty(message = "Name Cannot be Empty")
	@Size(min = 2, message = "Name Should be greater than 2 letters")
	private String customerName;

	@NotEmpty(message = "Contact Cannot be Empty")
	@Size(min = 10, max = 10, message = "Please Enter Proper Contact!")
	private String mobileNumber;

	@Email(message = "Please enter a valid e-mail address")
	@NotNull(message = "Email field is required")
	private String emailId;

	@NotNull(message = "Date Of Birth field is required")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Gender field is required Cannot be Empty")
	private String gender;

	@NotBlank(message = "Nationality field is required Cannot be Empty!")
	private String nationality;

	@Column(unique = true)
	@NotBlank(message = "AadharNumber field is required Cannot be Empty!")
	@Size(min = 12, max = 12, message = "Enter Proper Adhar Number!")
	private String aadharNumber;

	@Column(unique = true)
	@NotNull(message = "panNumber field is required")
	@Size(min = 10, max = 10, message = "Enter Proper PAN Number!")
	private String panNumber;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", nationality=" + nationality
				+ ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + "]";
	}

}
