package com.HomeLoanApplication.entites;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EMI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long EMIId;

	private LocalDate startDate;

	private LocalDate dueDate;

	private double emiAmount;

	private double loanAmount;

	private double interestAmount;

	public EMI() {
		super();
	}

	public EMI(LocalDate startDate, LocalDate dueDate, double emiAmount, double loanAmount, double interestAmount) {
		super();
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.emiAmount = emiAmount;
		this.loanAmount = loanAmount;
		this.interestAmount = interestAmount;
	}

	public Long getEMIId() {
		return EMIId;
	}

	public void setEMIId(Long eMIId) {
		EMIId = eMIId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
