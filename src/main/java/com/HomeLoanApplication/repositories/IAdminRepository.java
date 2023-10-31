package com.HomeLoanApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
	public Optional<Admin> findByUserNameAndPassword(String userName, String password);

	public Optional<Admin> findByUserName(String userName);

	public Optional<Admin> findByAdminContact(String adminContact);
}
