package com.HomeLoanApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	public Optional<Customer> findByUserNameAndPassword(String userName, String password);

	public Optional<Customer> findByUserName(String userName);

	public Optional<Customer> findByAadharNumber(String aadharNumber);

	public Optional<Customer> findByPanNumber(String panNumber);

}
