package com.HomeLoanApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.FinanceVerificationOfficer;

@Repository
public interface IFinanceVerificationOfficerRepository extends JpaRepository<FinanceVerificationOfficer, Integer> {

	public Optional<FinanceVerificationOfficer> findByUserNameAndPassword(String userName, String password);

	public Optional<FinanceVerificationOfficer> findByUserName(String userName);

	public Optional<FinanceVerificationOfficer> findByFinOfficerContact(String finOfficerContact);

}
