package com.HomeLoanApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HomeLoanApplication.entites.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUserName(String userName);

	public Optional<User> findByUserNameAndPassword(String userName, String password);
}
