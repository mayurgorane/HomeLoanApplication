package com.HomeLoanApplication.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User {

	@NotEmpty(message = "Name Cannot be Empty")
	@Size(min = 2, message = "Name Should be greater than 2 letters")
	private String adminName;

	@Column(unique = true)
	@NotEmpty(message = "Contact Cannot be Empty")
	@Size(min = 10, max = 10, message = "Please Enter Proper Contact!")
	private String adminContact;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	@Override
	public String toString() {

		return "Admin [adminName=" + adminName + ", adminContact=" + adminContact + "]" + super.toString();
	}

}
