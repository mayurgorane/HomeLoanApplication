package com.HomeLoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.LandVerificationOfficer;
import com.HomeLoanApplication.exceptions.LandVerificationOfficerNotFoundException;
import com.HomeLoanApplication.repositories.ILandVerificationOfficerRepository;
import com.HomeLoanApplication.repositories.IUserRepository;

@Service
public class LandVerificationOfficerService implements ILandVerificationOfficerService {

	@Autowired
	private ILandVerificationOfficerRepository iLandVerificationOfficerRepository;
	@Autowired
	IUserRepository userRepository;

	@Override
	public List<LandVerificationOfficer> getAllLandVerificationOfficer() {
		List<LandVerificationOfficer> LandVerificationOfficers = iLandVerificationOfficerRepository.findAll();
		if (LandVerificationOfficers.isEmpty()) {
			throw new LandVerificationOfficerNotFoundException(
					"No land Verification Officers present in the database!");
		} else {
			return iLandVerificationOfficerRepository.findAll();
		}
	}

	@Override
	public LandVerificationOfficer getLandVerificationOfficerById(int userId) {
		if (iLandVerificationOfficerRepository.findById(userId).isPresent()) {
			return iLandVerificationOfficerRepository.findById(userId).get();
		} else {
			throw new LandVerificationOfficerNotFoundException("No land Verification Officer Found for Id: " + userId);
		}
	}
	
	@Override
	public LandVerificationOfficer getLandVerificationOfficerByUserName(String username) {
		if (iLandVerificationOfficerRepository.findByUserName(username).isPresent()) {
			return iLandVerificationOfficerRepository.findByUserName(username).get();
		} else {
			throw new LandVerificationOfficerNotFoundException("No land Verification Officer Found for username: " + username);
		}
	}

	@Override
	public LandVerificationOfficer updateLandVerificationOfficer(int userId, LandVerificationOfficer landOfficer) {
		if (iLandVerificationOfficerRepository.findById(userId).isPresent()) {
			return iLandVerificationOfficerRepository.save(landOfficer);
		} else {
			throw new LandVerificationOfficerNotFoundException("No land Verification Officer Found for Id: " + userId);
		}
	}

	@Override
	public String deleteLandOfficerByUserId(int userId) {
		if (iLandVerificationOfficerRepository.findById(userId).isPresent()) {
			iLandVerificationOfficerRepository.deleteById(userId);
			return "land Verification Officer Deleted Successfully for Id:" + userId;
		} else {
			throw new LandVerificationOfficerNotFoundException("No land Verification Officer Found for Id: " + userId);
		}
	}

	@Override
	public String saveLandVerificationOfficer(LandVerificationOfficer landOfficer) {

		if (userRepository.findByUserName(landOfficer.getUserName()).isEmpty()) {
			if (iLandVerificationOfficerRepository.findByOfficerContact(landOfficer.getOfficerContact()).isEmpty()) {
				iLandVerificationOfficerRepository.save(landOfficer);
				return "success";
			} else {
				return "contact";
			}

		} else {
			return "username";
		}

	}
	
	@Override
	public boolean isValidLandVerificationOfficer(String userName, String password) {

		if (iLandVerificationOfficerRepository.findByUserNameAndPassword(userName, password).isPresent()) {
			return true;
		} else {
			throw new LandVerificationOfficerNotFoundException("Invalid Username or password!");
		}
	}
	
	@Override
	public LandVerificationOfficer getLandVerificationOfficerByUsername(String userName) {
		if(iLandVerificationOfficerRepository.findByUserName(userName).isPresent()) {
			return iLandVerificationOfficerRepository.findByUserName(userName).get();
		}
		return new LandVerificationOfficer();
	}
}