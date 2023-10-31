package com.HomeLoanApplication.services;

import java.util.List;

import com.HomeLoanApplication.entites.FinanceVerificationOfficer;

public interface IFinanceVerificationOfficerService {

	public String saveFinanceVerificationOfficer(FinanceVerificationOfficer financeVerificationOfficer);

	public List<FinanceVerificationOfficer> getAllFinanceVerificationOfficer();

	public FinanceVerificationOfficer getFinanceVerificationOfficerById(int userId);

	public FinanceVerificationOfficer updateFinanceVerificationOfficer(int userId,
			FinanceVerificationOfficer financeOfficer);

	public String deleteFinanceOfficerByUserId(int userId);

	boolean isValidFinanceVerificationOfficer(String userName, String password);

	FinanceVerificationOfficer getFinanceVerificationOfficerByUserName(String username);


}
