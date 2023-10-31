package com.HomeLoanApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.User;
import com.HomeLoanApplication.repositories.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> viewAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> viewUserById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String deleteUser(User user) {
		userRepository.delete(user);
		return "User Deleted Successfully!";
	}

	@Override
	public Optional<User> viewUserByUserName(String userName) {

		return userRepository.findByUserName(userName);
	}

	@Override
	public boolean isValidUser(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password).isPresent();
	}
}
