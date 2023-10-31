package com.HomeLoanApplication.services;

import com.HomeLoanApplication.entites.EMI;
import com.HomeLoanApplication.entites.LoanApplication;

public interface IEMIService {

	EMI addEMI(LoanApplication loanApplication, double rateOfInterest);

}
