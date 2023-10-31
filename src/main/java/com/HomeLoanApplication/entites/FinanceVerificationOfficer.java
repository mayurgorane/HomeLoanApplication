package com.HomeLoanApplication.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "FinanceVerificationOfficer")
public class FinanceVerificationOfficer extends User {

	@NotEmpty(message = "Name Cannot be Empty")
	@Size(min = 2, message = "Name Should be greater than 2 letters")
	private String finOfficerName;

	@Column(unique = true)
	@NotEmpty(message = "Contact Cannot be Empty")
	@Size(min = 10, max = 10, message = "Please Enter Proper Contact!")
	private String finOfficerContact;

	public String getFinOfficerName() {
		return finOfficerName;
	}

	public void setFinOfficerName(String finOfficerName) {
		this.finOfficerName = finOfficerName;
	}

	public String getFinOfficerContact() {
		return finOfficerContact;
	}

	public void setFinOfficerContact(String finOfficerContact) {
		this.finOfficerContact = finOfficerContact;
	}

	@Override
	public String toString() {
		super.toString();
		return "FinanceVerificationOfficer [finOfficerName=" + finOfficerName + ", finOfficerContact="
				+ finOfficerContact + "]";
	}

}
