package com.HomeLoanApplication.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.EMI;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.repositories.IEMIRepository;

@Service
public class EMIService implements IEMIService {

	@Autowired
	IEMIRepository emirepository;

	@Override
	public EMI addEMI(LoanApplication loanApplication, double rateOfInterest) {

		rateOfInterest = rateOfInterest / (12 * 100);
		int tenure = loanApplication.getTenure() * 12;
		double emiAmount = ((loanApplication.getLoanApprovedAmount() * rateOfInterest
				* Math.pow(1 + rateOfInterest, tenure)) / (Math.pow(1 + rateOfInterest, tenure) - 1));
		double interestAmount = (emiAmount * loanApplication.getTenure() * 12)
				- loanApplication.getLoanApprovedAmount();
		EMI emi = new EMI(LocalDate.now().plusDays(10),
				LocalDate.now().plusDays(10).plusYears(loanApplication.getTenure()), Math.round(emiAmount),
				loanApplication.getLoanApprovedAmount(), Math.round(interestAmount));
		return emirepository.save(emi);
	}

}
