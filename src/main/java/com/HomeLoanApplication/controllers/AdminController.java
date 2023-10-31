package com.HomeLoanApplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApplication.dto.UpdatePassowordDTO;
import com.HomeLoanApplication.entites.Admin;
import com.HomeLoanApplication.entites.FinanceVerificationOfficer;
import com.HomeLoanApplication.entites.LandVerificationOfficer;
import com.HomeLoanApplication.entites.LoanAgreement;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.User;
import com.HomeLoanApplication.services.AdminService;
import com.HomeLoanApplication.services.EMIService;
import com.HomeLoanApplication.services.FinanceVerificationOfficerService;
import com.HomeLoanApplication.services.LandVerificationOfficerService;
import com.HomeLoanApplication.services.LoanAgreementService;
import com.HomeLoanApplication.services.LoanApplicationService;

@RestController
@RequestMapping("admins")
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	LandVerificationOfficerService landVerificationOfficerService;
	@Autowired
	FinanceVerificationOfficerService financeVerificationOfficerService;
	@Autowired
	LoanApplicationService loanApplicationService;
	@Autowired
	LoanAgreementService loanAgreementService;
	@Autowired
	EMIService emiService;

	@PostMapping
	public ResponseInfo postAdmin(@RequestBody @Valid Admin admin) {
		String returnMessage=adminService.addAdmin(admin);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable("id") int userId) {
		return adminService.viewAdminById(userId);
	}

	@GetMapping("username/{username}")
	public Admin getAdminByUsername(@PathVariable("username") String username) {
		return adminService.viewAdminByUserName(username);
	}

	@GetMapping("/{name}")
	public Admin getAdminByName(@PathVariable("name") int userId) {
		return adminService.viewAdminById(userId);
	}

	@GetMapping("/{contact}")
	public Admin getAdminByContact(@PathVariable("contact") int userId) {
		return adminService.viewAdminById(userId);
	}

	@GetMapping
	public List<Admin> getAllAdmins() {
		return adminService.viewAllAdmins();
	}

	@PutMapping("/{username}")
	public ResponseInfo putAdmin(@PathVariable("username")  String username, @RequestBody @Valid Admin admin) {
		String returnMessage= adminService.updateAdmin(username, admin);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@DeleteMapping("/{id}")
	public String deleteAdmin(@PathVariable("id") int userId) {
		return adminService.deleteAdmin(userId);
	}

	@PostMapping("/validate")
	public boolean isValidAdmin(@RequestBody User user) {
		return adminService.isValidAdmin(user.getUserName(), user.getPassword());
	}
	
	@PostMapping("/updatepassword")
	public String updatePassword(@RequestBody UpdatePassowordDTO updto) {
	
		if(adminService.isAdminPresentByUserNameAndPassword(updto.getUserName(), updto.getCurrentPassword())) {
			
			
			return "success";
		}
		else {
			return "error";
		}
		
	}
	
	@PostMapping("/landverificationofficers")
	public ResponseInfo postLandVerificationOfficer(@RequestBody @Valid LandVerificationOfficer landVerificationOfficer) {
		String returnMessage=landVerificationOfficerService.saveLandVerificationOfficer(landVerificationOfficer);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@GetMapping("/landverificationofficers")
	public List<LandVerificationOfficer> getAllLandVerificationOfficer() {
		return landVerificationOfficerService.getAllLandVerificationOfficer();
	}

	@DeleteMapping("/landverificationofficers/{id}")
	public ResponseInfo deleteLandOfficerById(@PathVariable("id") int id) {
		System.out.println("Checking Land officer deletion");
		String returnMessage = landVerificationOfficerService.deleteLandOfficerByUserId(id);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@GetMapping("/landverificationofficers/username/{username}")
	public LandVerificationOfficer getLandVerificationOfficerByUsername(@PathVariable("username") String username) {
		return landVerificationOfficerService.getLandVerificationOfficerByUsername(username);
	}
	
	@PostMapping("/financeverificationofficers")
	public ResponseInfo postFinanceVerificationOfficer(@RequestBody FinanceVerificationOfficer financeVerificationOfficer) {
		String returnMessage= financeVerificationOfficerService.saveFinanceVerificationOfficer(financeVerificationOfficer);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@GetMapping("/financeverificationofficers")
	public List<FinanceVerificationOfficer> getAllFinanceVerificationOfficer() {
		return financeVerificationOfficerService.getAllFinanceVerificationOfficer();
	}

	@DeleteMapping("/financeverificationofficers/{id}")
	public ResponseInfo deleteFinanceOfficerById(@PathVariable("id") int id)
	{
		String returnMessage=financeVerificationOfficerService.deleteFinanceOfficerByUserId(id);
		ResponseInfo ri=new ResponseInfo(returnMessage,HttpStatus.OK.name());
		return ri;
	}
	
	@GetMapping("/loanapplications")
	public List<LoanApplication> getAllLoanApplications() {
		return loanApplicationService.retriveAllLoanApplication();
	}

	@GetMapping("/loanapplications/pending")
	public List<LoanApplication> getAllPendingLoanApplications() {
		return loanApplicationService.retriveAllPendingLoanApplication();
	}

	@DeleteMapping("/loanapplications/{id}")
	public ResponseInfo deleteLoanApplicationById(@PathVariable("id") long userId) {
		// System.out.print("Checking!");
		String returnMessage = loanApplicationService.deleteLoanApplicationById(userId);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@DeleteMapping("/loanagreements/{id}")
	public ResponseInfo deleteLoanAgreementById(@PathVariable("id") long userId) {
		// System.out.print("Checking!");
		String returnMessage = loanAgreementService.deleteLoanAgreement(userId);
		ResponseInfo ri = new ResponseInfo(returnMessage, HttpStatus.OK.name());
		return ri;
	}

	@PutMapping("/updateadminverificationstatus/{loanapplicationid}/accept")
	public LoanAgreement updateAdminVerficationStatusAccept(@PathVariable("loanapplicationid") long loanApplicationId) {

		LoanApplication loanApplication = loanApplicationService
				.updateAdminVerificationStatusAccepted(loanApplicationId);
		loanApplicationService.getHomeLoanBorrowingAmount(loanApplicationId, 9.5);
		return loanAgreementService.addLoanAgreement(loanApplication, emiService.addEMI(loanApplication, 9.5));
	}

	@PutMapping("/updateadminverificationstatus/{loanapplicationid}/reject")
	public LoanApplication updateAdminVerficationStatusReject(
			@PathVariable("loanapplicationid") long loanApplicationId) {

		LoanApplication loanApplication = loanApplicationService
				.updateAdminVerificationStatusRejected(loanApplicationId);
		return loanApplication;
	}

	@GetMapping("/loanagreements")
	public List<LoanAgreement> getAllLoanAgreements() {
		return loanAgreementService.viewAllLoanAgreements();
	}

}
