package com.HomeLoanApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.LandVerificationOfficer;

@Repository
public interface ILandVerificationOfficerRepository extends JpaRepository<LandVerificationOfficer, Integer> {
	Optional<LandVerificationOfficer> findByUserNameAndPassword(String userName, String password);

	Optional<LandVerificationOfficer> findByUserName(String userName);

	Optional<LandVerificationOfficer> findByOfficerContact(String officerContact);
}