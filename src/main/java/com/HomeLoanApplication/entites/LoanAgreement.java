package com.HomeLoanApplication.entites;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LoanAgreement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long loanAgreementId;
	@OneToOne(cascade = CascadeType.ALL)
	private LoanApplication loanApplication;
	@OneToOne(cascade = CascadeType.ALL)
	private EMI emi;
	private LocalDate agreementDate;
	private Status agreementStatus;

	public LoanAgreement() {
		super();
	}

	public LoanAgreement(LoanApplication loanApplication, EMI eMI) {
		super();
		this.loanApplication = loanApplication;
		this.emi = eMI;
		this.agreementDate = LocalDate.now();
		this.agreementStatus = Status.PENDING;
	}

	public long getLoanAgreementId() {
		return loanAgreementId;
	}

	public void setLoanAgreementId(long loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	public EMI getEMI() {
		return emi;
	}

	public void setEMI(EMI eMI) {
		this.emi = eMI;
	}

	public LocalDate getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(LocalDate agreementDate) {
		this.agreementDate = agreementDate;
	}

	public Status getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(Status agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

}