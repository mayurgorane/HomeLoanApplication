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

import com.HomeLoanApplication.entites.Admin;
import com.HomeLoanApplication.entites.LandVerificationOfficer;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.User;
import com.HomeLoanApplication.services.LandVerificationOfficerService;
import com.HomeLoanApplication.services.LoanApplicationService;

@RestController
@RequestMapping("/landverificationofficers")
@CrossOrigin(origins = "http://localhost:4200/")
public class LandVerificationOfficerController {
	@Autowired
	LandVerificationOfficerService landVerificationOfficerService;
	@Autowired
	LoanApplicationService loanApplicationService;

	@PostMapping("/validate")
	public boolean isValidAdmin(@RequestBody User user) {
		return landVerificationOfficerService.isValidLandVerificationOfficer(user.getUserName(), user.getPassword());
	}

	@GetMapping("/loanapplications")
	public List<LoanApplication> getAllLoanApplications() {
		return loanApplicationService.retriveAllLoanApplication();
	}
	
	@GetMapping("username/{username}")
	public LandVerificationOfficer getLandOfficerByUsername(@PathVariable("username") String username) {
		return landVerificationOfficerService.getLandVerificationOfficerByUserName(username);
	}
	
	@GetMapping("/loanapplications/pending")
	public List<LoanApplication> getAllWaitingForLandApprovalLoanApplications() {
		return loanApplicationService.retriveAllWaitingForLandApprovalLoanApplication();
	}
	
	@PutMapping("/updatelandverificationstatus/{loanapplicationid}/accept")
	public LoanApplication updateLandVerficationStatusAccept(@PathVariable("loanapplicationid") long loanApplicationId) {
		return loanApplicationService.updateLandVerificationStatusAccept(loanApplicationId);
	}
	
	@PutMapping("/updatelandverificationstatus/{loanapplicationid}/reject")
	public LoanApplication updateLandVerficationStatusReject(@PathVariable("loanapplicationid") long loanApplicationId) {
		return loanApplicationService.updateLandVerificationStatusReject(loanApplicationId);
	}
	
	
}
