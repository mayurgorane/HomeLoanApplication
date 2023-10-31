package com.HomeLoanApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApplication.entites.FinanceVerificationOfficer;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.User;
import com.HomeLoanApplication.services.FinanceVerificationOfficerService;
import com.HomeLoanApplication.services.LoanApplicationService;

@RestController
@RequestMapping("/financeverificationofficers")
@CrossOrigin(origins = "http://localhost:4200/")
public class FinanceVerificationOfficerController {
	@Autowired
	FinanceVerificationOfficerService financeVerificationOfficerService;
	@Autowired
	LoanApplicationService loanApplicationService;

	@PostMapping("/validate")
	public boolean isValidAdmin(@RequestBody User user) {
		return financeVerificationOfficerService.isValidFinanceVerificationOfficer(user.getUserName(),
				user.getPassword());
	}

	@GetMapping("/loanapplications")
	public List<LoanApplication> getAllLoanApplications() {
		return loanApplicationService.retriveAllLoanApplication();
	}
	
	@GetMapping("/loanapplications/pending")
	public List<LoanApplication> getAllWaitingForFinanceApprovalLoanApplications() {
		return loanApplicationService.retriveAllWaitingForFinanceApprovalLoanApplication();
	}
	
	
	@GetMapping("username/{username}")
	public FinanceVerificationOfficer getFinanceOfficerByUsername(@PathVariable("username") String username) {
		return financeVerificationOfficerService.getFinanceVerificationOfficerByUserName(username);
	}

	@PutMapping("/updatefinanceverificationstatus/{loanapplicationid}/accept")
	public LoanApplication updateFinanceVerificationStatusAccept(@PathVariable("loanapplicationid") long loanApplicationId) {
		return loanApplicationService.updateFinanceVerificationStatusAccept(loanApplicationId);
	}
	
	@PutMapping("/updatefinanceverificationstatus/{loanapplicationid}/reject")
	public LoanApplication updateFinanceVerificationStatusReject(@PathVariable("loanapplicationid") long loanApplicationId) {
		return loanApplicationService.updateFinanceVerificationStatusReject(loanApplicationId);
	}

}
