package com.HomeLoanApplication.entites;

import javax.persistence.Embeddable;

@Embeddable
public class Document {
	private String addressOfProperty;
	private Double valuationOfProperty;
	private Double totalAnnualIncome;

	public String getAddressOfProperty() {
		return addressOfProperty;
	}

	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
	}

	public Double getValuationOfProperty() {
		return valuationOfProperty;
	}

	public void setValuationOfProperty(Double valuationOfProperty) {
		this.valuationOfProperty = valuationOfProperty;
	}

	public Double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}

	public void setTotalAnnualIncome(Double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}

	@Override
	public String toString() {
		return "Document [addressOfProperty=" + addressOfProperty + ", valuationOfProperty=" + valuationOfProperty
				+ ", totalAnnualIncome=" + totalAnnualIncome + "]";
	}

}
