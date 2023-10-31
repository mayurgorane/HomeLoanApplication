package com.HomeLoanApplication.entites;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long applicationId;
	private LocalDate applicationDate;

	@NotNull(message = "Loan Applied Amount is required Cannot be Empty")
	@Min(value = 100000)
	@Max(value = 5000000)
	private double loanAppliedAmount;

	private double loanApprovedAmount;

	private boolean documentUploaded;

	private boolean landVerificationApproval;

	private boolean financeVerificationApproval;

	private boolean adminApproval;

	private Status status;

	@NotNull(message = "Tenure is required Cannot be Empty")
	@Min(value = 1)
	@Max(value = 35)
	private int tenure;

	@OneToOne
	private Customer customer;

	@Embedded
	private Document document;

	public LoanApplication() {
		super();
	}

	public LoanApplication(double loanAppliedAmount, int tenure, Customer customer) {
		super();
		this.loanAppliedAmount = loanAppliedAmount;
		this.tenure = tenure;
		this.customer = customer;
		this.documentUploaded = false;
		this.adminApproval = false;
		this.financeVerificationApproval = false;
		this.landVerificationApproval = false;
		this.applicationDate = LocalDate.now();
		this.status = Status.DOCUMENTS_NOT_UPLOADED;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public double getLoanAppliedAmount() {
		return loanAppliedAmount;
	}

	public void setLoanAppliedAmount(double loanAppliedAmount) {
		this.loanAppliedAmount = loanAppliedAmount;
	}

	public double getLoanApprovedAmount() {
		return loanApprovedAmount;
	}

	public void setLoanApprovedAmount(double loanApprovedAmount) {
		this.loanApprovedAmount = loanApprovedAmount;
	}

	public boolean isLandVerificationApproval() {
		return landVerificationApproval;
	}

	public void setLandVerificationApproval(boolean landVerificationApproval) {
		this.landVerificationApproval = landVerificationApproval;
	}

	public boolean isFinanceVerificationApproval() {
		return financeVerificationApproval;
	}

	public void setFinanceVerificationApproval(boolean financeVerificationApproval) {
		this.financeVerificationApproval = financeVerificationApproval;
	}

	public boolean isAdminApproval() {
		return adminApproval;
	}

	public void setAdminApproval(boolean adminApproval) {
		this.adminApproval = adminApproval;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public boolean isDocumentUploaded() {
		return documentUploaded;
	}

	public void setDocumentUploaded(boolean documentUploaded) {
		this.documentUploaded = documentUploaded;
	}

	public Document getDocuments() {
		return document;
	}

	public void setDocuments(Document document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "LoanApplication [applicationId=" + applicationId + ", applicationDate=" + applicationDate
				+ ", loanAppliedAmount=" + loanAppliedAmount + ", loanApprovedAmount=" + loanApprovedAmount
				+ ", documentUploaded=" + documentUploaded + ", landVerificationApproval=" + landVerificationApproval
				+ ", financeVerificationApproval=" + financeVerificationApproval + ", adminApproval=" + adminApproval
				+ ", status=" + status + ", tenure=" + tenure + ", customer=" + customer + "]";
	}

}
