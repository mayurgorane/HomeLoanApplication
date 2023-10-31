package com.HomeLoanApplication.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApplication.dto.LoanApplicationDTO;
import com.HomeLoanApplication.entites.Customer;
import com.HomeLoanApplication.entites.Document;
import com.HomeLoanApplication.entites.LoanAgreement;
import com.HomeLoanApplication.entites.LoanApplication;
import com.HomeLoanApplication.entites.User;
import com.HomeLoanApplication.services.CustomerService;
import com.HomeLoanApplication.services.LoanAgreementService;
import com.HomeLoanApplication.services.LoanApplicationService;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	LoanApplicationService loanApplicationService;
	@Autowired
	LoanAgreementService loanAgreementService;

	@GetMapping("/{custId}")
	public Customer getCustomerById(@PathVariable("custId") int custId) {
		return customerService.viewCustomer(custId);
	}
	
	@GetMapping("/username/{username}")
	public Customer getCustomerByUsername(@PathVariable("username") String username) {
		return customerService.viewCustomerByUserName(username);
	}

	@GetMapping("loanapplications/{userName}")
	public LoanApplication getLoanApplicationByUserName(@PathVariable("userName") String UserName) {
		return loanApplicationService.viewLoanApplicationByCustomer(customerService.viewCustomerByUserName(UserName));
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.viewAllCustomers();
	}

	@PostMapping
	public String postCustomer(@RequestBody @Valid Customer customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping
	public Customer putCustomer(@RequestBody @Valid Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping("/{custId}")
	public String deleteCustomer(@PathVariable("custId") int custId) {
		customerService.deleteCustomer(custId);
		return "Customer Deleted Sucessfully";
	}

	@PostMapping("/validate")
	public boolean isValidCustomer(@RequestBody @Valid User user) {
		return customerService.isValidCustomer(user.getUserName(), user.getPassword());
	}

//	@PostMapping("/loanapplications")
//	public String postLoanApplication(@RequestBody @Valid LoanApplicationDTO loanApplicationDTO) {
//		return loanApplicationService.addLoanApplication(
//				customerService.viewCustomerByUserName(loanApplicationDTO.getUserName()),
//				loanApplicationDTO.getLoanAppliedAmount(), loanApplicationDTO.getTenure());
//	}
	
	@PostMapping("/loanapplications/{username}")
	public String addLoanApplication(@RequestBody @Valid LoanApplicationDTO loanApplicationDTO,@PathVariable String username) {
		System.out.println("CUSTOMERRRRRRRRR"+loanApplicationDTO.getLoanAppliedAmount());
		return loanApplicationService.addLoanApplication(
				customerService.viewCustomerByUserName(username),
				loanApplicationDTO.getLoanAppliedAmount(), loanApplicationDTO.getTenure());
	}

	@PostMapping("/loanapplications/documents/{loanApplicationId}")
	public LoanApplication postDoucments(@RequestBody Document document,
			@PathVariable("loanApplicationId") Long loanApplicationId) {
		return loanApplicationService.addDocuments(document, loanApplicationId);
	}

	@PutMapping("/loanagreements/accepted/{loanAgreementId}")
	public LoanAgreement putLoanAgreementAccepted(@PathVariable("loanAgreementId") Long loanAgreementId) {
		return loanAgreementService.updateLoanAgreementStatusAccepted(loanAgreementId);
	}

	@PutMapping("/loanagreements/rejected/{loanAgreementId}")
	public LoanAgreement putLoanAgreementRejected(@PathVariable("loanAgreementId") Long loanAgreementId) {
		return loanAgreementService.updateLoanAgreementStatusRejected(loanAgreementId);
	}

	@GetMapping("/customers/applicationdate/{applicationDate}")
	public List<Customer> customerByaAplicationDate(@PathVariable("applicationDate") String appDate) {

		LocalDate applicationDate = LocalDate.parse(appDate);
		return loanApplicationService.viewCustomerList(applicationDate);

	}
	
	@GetMapping("/loanagreements/{username}")
	public LoanAgreement getLoanAgreementByUserName(@PathVariable("username") String username)
	{
		LoanApplication loanapp=loanApplicationService.viewLoanApplicationByCustomer(customerService.viewCustomerByUserName(username));
		return loanAgreementService.getLoanAgreementByLoanApp(loanapp);
	}

}
