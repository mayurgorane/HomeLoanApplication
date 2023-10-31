package com.HomeLoanApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.LoanAgreement;
import com.HomeLoanApplication.entites.LoanApplication;

@Repository
public interface ILoanAgreementRepository extends JpaRepository<LoanAgreement, Long> {
	public Optional<LoanAgreement> findByLoanApplication(LoanApplication loanApplication);
}
