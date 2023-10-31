package com.HomeLoanApplication.services;

import java.time.LocalDate;
import java.util.List;

import com.HomeLoanApplication.entites.Customer;
import com.HomeLoanApplication.entites.Document;
import com.HomeLoanApplication.entites.LoanApplication;

public interface ILoanApplicationService {

	public List<LoanApplication> retriveAllLoanApplication();

	public LoanApplication retriveLoanApplicationById(Long applicationId);

	public LoanApplication updateLoanApplication(LoanApplication loanApp);

	String addLoanApplication(Customer customer, double loanAppliedAmount, int tenure);

	LoanApplication viewLoanApplicationByCustomer(Customer customer);

	LoanApplication addDocuments(Document document, Long loanApplicationId);

	double getHomeLoanBorrowingAmount(Long loanApplicationId, double rateOfInterest);

	List<Customer> viewCustomerList(LocalDate dateOfApplication);

	LoanApplication updateAdminVerificationStatusAccepted(long loanApplicationId);

	String deleteLoanApplicationById(Long applicationId);

	LoanApplication updateLandVerificationStatusAccept(long loanApplicationId);

	LoanApplication updateLandVerificationStatusReject(long loanApplicationId);

	LoanApplication updateFinanceVerificationStatusAccept(long loanApplicationId);

	LoanApplication updateFinanceVerificationStatusReject(long loanApplicationId);

	List<LoanApplication> retriveAllWaitingForLandApprovalLoanApplication();

	List<LoanApplication> retriveAllWaitingForFinanceApprovalLoanApplication();

	LoanApplication updateAdminVerificationStatusRejected(long loanApplicationId);

	List<LoanApplication> retriveAllPendingLoanApplication();
}
