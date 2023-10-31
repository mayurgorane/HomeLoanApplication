package com.HomeLoanApplication.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.EMI;
import com.HomeLoanApplication.entites.LoanAgreement;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.Status;
import com.HomeLoanApplication.exceptions.LoanAgreementNotFoundException;
import com.HomeLoanApplication.repositories.ILoanAgreementRepository;

@Service
public class LoanAgreementService implements ILoanAgreementService {

	@Autowired
	ILoanAgreementRepository loanAgreementRepository;

	@Override
	public LoanAgreement addLoanAgreement(LoanApplication loanApplication, EMI emi) {
		return loanAgreementRepository.save(new LoanAgreement(loanApplication, emi));
	}

	@Override
	public List<LoanAgreement> viewAllLoanAgreements() {
		if (loanAgreementRepository.findAll().isEmpty()) {
			throw new LoanAgreementNotFoundException("No Loan Agreements found!");
		} else {
			return loanAgreementRepository.findAll();
		}
	}
	
	@Override
	public LoanAgreement getLoanAgreementByLoanApp(LoanApplication loanapplication)
	{
		if(loanAgreementRepository.findByLoanApplication(loanapplication).isPresent()) {
		return loanAgreementRepository.findByLoanApplication(loanapplication).get();
		}
		else
		{
			throw new LoanAgreementNotFoundException("No Loan Agreements found!");
		}
	}
	
	@Override
	public LoanAgreement updateLoanAgreementStatusAccepted(long loanAgreementId) {
		if (loanAgreementRepository.findById(loanAgreementId).isEmpty()) {
			throw new LoanAgreementNotFoundException("Loan Agreement not found for Id:" + loanAgreementId);
		}
		LoanAgreement loanAgreement = loanAgreementRepository.findById(loanAgreementId).get();
		System.out.println(loanAgreement.getAgreementDate().plusDays(10).isBefore(LocalDate.now()));
		if (loanAgreement.getAgreementDate().plusDays(10).isBefore(LocalDate.now())) {
			loanAgreement.setAgreementStatus(Status.REJECTED);
			return loanAgreementRepository.save(loanAgreement);
		} else {
			loanAgreement.setAgreementStatus(Status.ACCEPTED);
			return loanAgreementRepository.save(loanAgreement);
		}
	}

	@Override
	public LoanAgreement updateLoanAgreementStatusRejected(long loanAgreementId) {
		if (loanAgreementRepository.findById(loanAgreementId).isEmpty()) {
			throw new LoanAgreementNotFoundException("Loan Agreement not found for Id:" + loanAgreementId);
		}
		LoanAgreement loanAgreement = loanAgreementRepository.findById(loanAgreementId).get();
		loanAgreement.setAgreementStatus(Status.REJECTED);
		return loanAgreementRepository.save(loanAgreement);

	}

	@Override
	public String deleteLoanAgreement(long loanAgreementId) {
		if (loanAgreementRepository.findById(loanAgreementId).isEmpty()) {
			throw new LoanAgreementNotFoundException("Loan Agreement not found for Id:" + loanAgreementId);
		} else {
			loanAgreementRepository.deleteById(loanAgreementId);
			return "Loan Agreement Deleted Successfully for Id:" + loanAgreementId;
		}
	}

}
