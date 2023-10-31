package com.HomeLoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.FinanceVerificationOfficer;
import com.HomeLoanApplication.exceptions.FinanceVerificationOfficerNotFoundException;
import com.HomeLoanApplication.repositories.IFinanceVerificationOfficerRepository;
import com.HomeLoanApplication.repositories.IUserRepository;

@Service
public class FinanceVerificationOfficerService implements IFinanceVerificationOfficerService {

	@Autowired
	private IFinanceVerificationOfficerRepository iFinanceVerificationOfficerRepository;
	@Autowired
	IUserRepository userRepository;

	@Override
	public List<FinanceVerificationOfficer> getAllFinanceVerificationOfficer() {
		List<FinanceVerificationOfficer> financeVerificationOfficers = iFinanceVerificationOfficerRepository.findAll();
		if (financeVerificationOfficers.isEmpty()) {
			throw new FinanceVerificationOfficerNotFoundException(
					"No Finance Verification Officers present in the database!");
		} else {
			return iFinanceVerificationOfficerRepository.findAll();
		}
	}

	@Override
	public FinanceVerificationOfficer getFinanceVerificationOfficerById(int userId) {
		if (iFinanceVerificationOfficerRepository.findById(userId).isPresent()) {
			return iFinanceVerificationOfficerRepository.findById(userId).get();
		} else {
			throw new FinanceVerificationOfficerNotFoundException(
					"No Finance Verification Officer Found for Id: " + userId);
		}
	}

	@Override
	public FinanceVerificationOfficer updateFinanceVerificationOfficer(int userId,
			FinanceVerificationOfficer financeOfficer) {
		if (iFinanceVerificationOfficerRepository.findById(userId).isPresent()) {
			return iFinanceVerificationOfficerRepository.save(financeOfficer);
		} else {
			throw new FinanceVerificationOfficerNotFoundException(
					"No Finance Verification Officer Found for Id: " + userId);
		}
	}

	@Override
	public String deleteFinanceOfficerByUserId(int userId) {
		if (iFinanceVerificationOfficerRepository.findById(userId).isPresent()) {
			iFinanceVerificationOfficerRepository.deleteById(userId);
			return "Finance Verification Officer Deleted Successfully for Id:" + userId;
		} else {
			throw new FinanceVerificationOfficerNotFoundException(
					"No Finance Verification Officer Found for Id: " + userId);
		}
	}

	@Override
	public String saveFinanceVerificationOfficer(FinanceVerificationOfficer financeOfficer) {

		if (userRepository.findByUserName(financeOfficer.getUserName()).isEmpty()) {
			if (iFinanceVerificationOfficerRepository.findByFinOfficerContact(financeOfficer.getFinOfficerContact())
					.isEmpty()) {
				iFinanceVerificationOfficerRepository.save(financeOfficer);
				return "success";
				}
			else {
				return "contact";
			}

		} else {
			return "username";
		}

	}
	
	@Override
	public boolean isValidFinanceVerificationOfficer(String userName, String password) {

		if (iFinanceVerificationOfficerRepository.findByUserNameAndPassword(userName, password).isPresent()) {
			return true;
		} else {
			throw new FinanceVerificationOfficerNotFoundException("Invalid Username or password!");
		}
	}
	
	@Override
	public FinanceVerificationOfficer getFinanceVerificationOfficerByUserName(String username) {
		if (iFinanceVerificationOfficerRepository.findByUserName(username).isPresent()) {
			return iFinanceVerificationOfficerRepository.findByUserName(username).get();
		} else {
			throw new FinanceVerificationOfficerNotFoundException("No Finance Verification Officer Found for username: " + username);
		}
	}

	

}