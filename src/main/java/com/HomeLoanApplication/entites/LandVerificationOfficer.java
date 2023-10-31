package com.HomeLoanApplication.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "LandVerificationOfficer")
public class LandVerificationOfficer extends User {

	@NotEmpty(message = "Name Cannot be Empty")
	@Size(min = 2, message = "Name Should be greater than 2 letters")
	private String officerName;

	@Column(unique = true)
	@NotEmpty(message = "Contact Cannot be Empty")
	@Size(min = 10, max = 10, message = "Please Enter Proper Contact!")
	private String officerContact;

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getOfficerContact() {
		return officerContact;
	}

	public void setOfficerContact(String officerContact) {
		this.officerContact = officerContact;
	}

	@Override
	public String toString() {
		super.toString();
		return "LandVerificationOfficer [officerName=" + officerName + ", officerContact=" + officerContact + "]";
	}

}
