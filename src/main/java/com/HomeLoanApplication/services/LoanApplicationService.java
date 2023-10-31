package com.HomeLoanApplication.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.Customer;
import com.HomeLoanApplication.entites.Document;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.Status;
import com.HomeLoanApplication.exceptions.LoanApplicationNotFoundException;
import com.HomeLoanApplication.exceptions.StatusException;
import com.HomeLoanApplication.repositories.ILoanAgreementRepository;
import com.HomeLoanApplication.repositories.ILoanApplicationRepository;

@Service
public class LoanApplicationService implements ILoanApplicationService {

	@Autowired
	ILoanApplicationRepository loanApplicationRepository;
	@Autowired
	ILoanAgreementRepository loanAgreementRepository;

	@Override
	public List<LoanApplication> retriveAllLoanApplication() {
		if (loanApplicationRepository.findAll().isEmpty())
			throw new LoanApplicationNotFoundException("No Loan Applications found!");
		else
			return loanApplicationRepository.findAll();
	}

	@Override
	public LoanApplication retriveLoanApplicationById(Long applicationId) {
		if (loanApplicationRepository.findById(applicationId).isPresent()) {
			return loanApplicationRepository.findById(applicationId).get();
		} else {
			throw new LoanApplicationNotFoundException("Loan Application Not Found for Id:" + applicationId);
		}
	}

	@Override
	public String addLoanApplication(Customer customer, double loanAppliedAmount, int tenure) {
		if (loanApplicationRepository.findByCustomer(customer).isPresent()) {
			return "One customer can have only one home loan!";
		} else {
			return loanApplicationRepository.save(new LoanApplication(loanAppliedAmount, tenure, customer)).toString();
		}
	}

	@Override
	public LoanApplication updateLoanApplication(LoanApplication loanApp) {
		return loanApplicationRepository.save(loanApp);
	}

	@Override
	public String deleteLoanApplicationById(Long applicationId) {
		if(loanApplicationRepository.findById(applicationId).isPresent())
		{
			LoanApplication la=loanApplicationRepository.findById(applicationId).get();
			if(loanAgreementRepository.findByLoanApplication(la).isPresent())
			{
				loanAgreementRepository.delete(loanAgreementRepository.findByLoanApplication(la).get());
				loanApplicationRepository.deleteById(applicationId);
				return "Deleted Successfully";
			}
			else
			{
				loanApplicationRepository.deleteById(applicationId);
				return "Deleted Successfully";
			}
		}else
		{
			return "Loan Application Not Found";
		}

	}

	@Override
	public LoanApplication viewLoanApplicationByCustomer(Customer customer) {

		if (loanApplicationRepository.findByCustomer(customer).isPresent()) {
			return loanApplicationRepository.findByCustomer(customer).get();
		} else {
			throw new LoanApplicationNotFoundException("No Loan Application found for the Customer");
		}

	}

	@Override
	public LoanApplication updateFinanceVerificationStatusAccept(long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				throw new StatusException("Land Verification is Pending!");
			case WAITING_FOR_FINANCE_APPROVAL:
				loanapp.setFinanceVerificationApproval(true);
				loanapp.setStatus(Status.PENDING);
				return loanApplicationRepository.save(loanapp);
			case PENDING:
				throw new StatusException("Finance Verification is already done");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}
	
	@Override
	public LoanApplication updateFinanceVerificationStatusReject(long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				throw new StatusException("Land Verification is Pending!");
			case WAITING_FOR_FINANCE_APPROVAL:
				loanapp.setFinanceVerificationApproval(true);
				loanapp.setStatus(Status.REJECTED_BY_FINANCE_OFFICER);
				return loanApplicationRepository.save(loanapp);
			case PENDING:
				throw new StatusException("Finance Verification is already done");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}
	
	@Override
	public LoanApplication updateLandVerificationStatusAccept(long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				loanapp.setLandVerificationApproval(true);
				loanapp.setStatus(Status.WAITING_FOR_FINANCE_APPROVAL);
				return loanApplicationRepository.save(loanapp);
			case WAITING_FOR_FINANCE_APPROVAL:
				throw new StatusException("Land Verification is Already Done!");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}
	
	@Override
	public LoanApplication updateLandVerificationStatusReject(long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				loanapp.setLandVerificationApproval(true);
				loanapp.setStatus(Status.REJECTED_BY_LAND_OFFICER);
				return loanApplicationRepository.save(loanapp);
			case WAITING_FOR_FINANCE_APPROVAL:
				throw new StatusException("Land Verification is Already Done!");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}

	@Override
	public LoanApplication updateAdminVerificationStatusAccepted(long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				throw new StatusException("Land Verification is Pending!");
			case WAITING_FOR_FINANCE_APPROVAL:
				throw new StatusException("Finance Verification is Pending!");
			case PENDING:
				loanapp.setAdminApproval(true);
				loanapp.setStatus(Status.APPROVED);
				return loanApplicationRepository.save(loanapp);
			case APPROVED:
				throw new StatusException("Approval is already done");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}

	@Override
	public LoanApplication addDocuments(Document document, Long loanApplicationId) {
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			if (loanapp.isDocumentUploaded() == false) {
				loanapp.setDocuments(document);
				loanapp.setDocumentUploaded(true);
				loanapp.setStatus(Status.WAITING_FOR_LAND_VERIFICATION_APPROVAL);
				return loanApplicationRepository.save(loanapp);
			} else {
				throw new StatusException("Documents Are Already Uploaded!");
			}
		}
	}

	@Override
	public double getHomeLoanBorrowingAmount(Long loanApplicationId, double rateOfInterest) {

		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		}

		LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId).get();

		rateOfInterest = rateOfInterest / (12 * 100);
		int tenure = loanApplication.getTenure() * 12;
		double totalPayableAmount = ((loanApplication.getLoanAppliedAmount() * rateOfInterest
				* Math.pow(1 + rateOfInterest, tenure)) / (Math.pow(1 + rateOfInterest, tenure) - 1)) * 12
				* loanApplication.getTenure();
		//System.out.println("totalPayableAmount :"+totalPayableAmount);
		double totalBorrowingCapacity = loanApplication.getDocuments().getTotalAnnualIncome() * 0.6
				* loanApplication.getTenure();
		//System.out.println("totalBorrowingCapacity"+totalBorrowingCapacity);
		if (loanApplication.getLoanAppliedAmount() < (loanApplication.getDocuments().getValuationOfProperty() * 0.8)) {
			if (totalPayableAmount < totalBorrowingCapacity) {
				loanApplication.setLoanApprovedAmount(Math.round(loanApplication.getLoanAppliedAmount()));
				return Math.round(loanApplication.getLoanAppliedAmount());
			} else {
				double approvedLoan=((totalBorrowingCapacity/((12* loanApplication.getTenure())))*(Math.pow(1 + rateOfInterest, tenure) - 1))/(rateOfInterest
						* Math.pow(1 + rateOfInterest, tenure));
				loanApplication.setLoanApprovedAmount(Math.round(approvedLoan));
				return Math.round(approvedLoan);
			}
		} else {
			if ((loanApplication.getDocuments().getValuationOfProperty() * 0.8) < totalBorrowingCapacity) {
				loanApplication.setLoanApprovedAmount(Math.round((loanApplication.getDocuments().getValuationOfProperty() * 0.8)));
				return (Math.round((loanApplication.getDocuments().getValuationOfProperty() * 0.8)));
			} else {
				double approvedLoan=((totalBorrowingCapacity/((12* loanApplication.getTenure())))*(Math.pow(1 + rateOfInterest, tenure) - 1))/(rateOfInterest
						* Math.pow(1 + rateOfInterest, tenure));
				loanApplication.setLoanApprovedAmount(Math.round(approvedLoan));
				return Math.round(approvedLoan);
			}
		}
	}

	@Override
	public List<Customer> viewCustomerList(LocalDate dateOfApplication) {

		List<LoanApplication> loanApps = loanApplicationRepository.findByApplicationDate(dateOfApplication);

		if (loanApps.isEmpty()) {
			throw new LoanApplicationNotFoundException(" with DateOfApplication:" + dateOfApplication);
		} else {
			List<Customer> customers = new ArrayList<Customer>();
			for (int i = 0; i < loanApps.size(); i++) {
				customers.add(loanApps.get(i).getCustomer());
			}

			return customers;
		}
	}

	@Override
	public List<LoanApplication> retriveAllPendingLoanApplication() {
		
		if (loanApplicationRepository.findAll().isEmpty())
			throw new LoanApplicationNotFoundException("No Loan Applications found!");
		else
			{
			List<LoanApplication> allLoanApplication=loanApplicationRepository.findAll();
			List<LoanApplication> allPendingLoanApplication =new ArrayList<LoanApplication>();
			for (int i = 0; i < allLoanApplication.size(); i++) {
				if(allLoanApplication.get(i).getStatus()==Status.PENDING)
				allPendingLoanApplication.add(allLoanApplication.get(i));
			}
			return allPendingLoanApplication;
			}
	}
	
	@Override
	public List<LoanApplication> retriveAllWaitingForLandApprovalLoanApplication() {
		
		if (loanApplicationRepository.findAll().isEmpty())
			throw new LoanApplicationNotFoundException("No Loan Applications found!");
		else
			{
			List<LoanApplication> allLoanApplication=loanApplicationRepository.findAll();
			List<LoanApplication> allPendingLoanApplication =new ArrayList<LoanApplication>();
			for (int i = 0; i < allLoanApplication.size(); i++) {
				if(allLoanApplication.get(i).getStatus()==Status.WAITING_FOR_LAND_VERIFICATION_APPROVAL)
				allPendingLoanApplication.add(allLoanApplication.get(i));
			}
			return allPendingLoanApplication;
			}
	}
	
	@Override
	public List<LoanApplication> retriveAllWaitingForFinanceApprovalLoanApplication() {
		
		if (loanApplicationRepository.findAll().isEmpty())
			throw new LoanApplicationNotFoundException("No Loan Applications found!");
		else
			{
			List<LoanApplication> allLoanApplication=loanApplicationRepository.findAll();
			List<LoanApplication> allPendingLoanApplication =new ArrayList<LoanApplication>();
			for (int i = 0; i < allLoanApplication.size(); i++) {
				if(allLoanApplication.get(i).getStatus()==Status.WAITING_FOR_FINANCE_APPROVAL)
				allPendingLoanApplication.add(allLoanApplication.get(i));
			}
			return allPendingLoanApplication;
			}
	}
	
	@Override
	public LoanApplication updateAdminVerificationStatusRejected(long loanApplicationId) {
		
		if (loanApplicationRepository.findById(loanApplicationId).isEmpty()) {
			throw new LoanApplicationNotFoundException("No Loan Application Found for id:" + loanApplicationId);
		} else {
			LoanApplication loanapp = loanApplicationRepository.findById(loanApplicationId).get();
			Status status = loanapp.getStatus();
			switch (status) {
			case DOCUMENTS_NOT_UPLOADED:
				throw new StatusException("Documents are not uploded");
			case WAITING_FOR_LAND_VERIFICATION_APPROVAL:
				throw new StatusException("Land Verification is Pending!");
			case WAITING_FOR_FINANCE_APPROVAL:
				throw new StatusException("Finance Verification is Pending!");
			case PENDING:
				loanapp.setAdminApproval(false);
				loanapp.setStatus(Status.REJECTED_BY_ADMIN);
				return loanApplicationRepository.save(loanapp);
			case APPROVED:
				throw new StatusException("Approval is already done");
			case REJECTED_BY_ADMIN:
				throw new StatusException("Rejection is already done");
			default:
				throw new StatusException("Unexpected Error!");
			}
		}
	}

}