package com.HomeLoanApplication.services;

import java.util.List;

import com.HomeLoanApplication.entites.LandVerificationOfficer;

public interface ILandVerificationOfficerService {

	public String saveLandVerificationOfficer(LandVerificationOfficer landVerificationOfficer);

	public List<LandVerificationOfficer> getAllLandVerificationOfficer();

	public LandVerificationOfficer getLandVerificationOfficerById(int userId);

	public LandVerificationOfficer updateLandVerificationOfficer(int userId, LandVerificationOfficer landOfficer);

	public String deleteLandOfficerByUserId(int userId);

	boolean isValidLandVerificationOfficer(String userName, String password);

	LandVerificationOfficer getLandVerificationOfficerByUsername(String userName);

	LandVerificationOfficer getLandVerificationOfficerByUserName(String username);


}
