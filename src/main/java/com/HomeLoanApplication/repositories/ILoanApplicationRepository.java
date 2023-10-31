package com.HomeLoanApplication.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.Customer;
import com.HomeLoanApplication.entites.LoanApplication;

@Repository
public interface ILoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

	public Optional<LoanApplication> findByCustomer(Customer customer);

	public List<LoanApplication> findByApplicationDate(LocalDate dateOfApplication);

}
