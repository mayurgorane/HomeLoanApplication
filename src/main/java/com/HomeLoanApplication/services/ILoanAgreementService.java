package com.HomeLoanApplication.services;

import java.util.List;

import com.HomeLoanApplication.entites.EMI;
import com.HomeLoanApplication.entites.LoanAgreement;
import com.HomeLoanApplication.entites.LoanApplication;

public interface ILoanAgreementService {

	LoanAgreement addLoanAgreement(LoanApplication loanApplication, EMI emi);

	LoanAgreement updateLoanAgreementStatusRejected(long loanAgreementId);

	LoanAgreement updateLoanAgreementStatusAccepted(long loanAgreementId);

	String deleteLoanAgreement(long loanAgreementId);

	List<LoanAgreement> viewAllLoanAgreements();
	
	LoanAgreement getLoanAgreementByLoanApp(LoanApplication loanapplication);

}
