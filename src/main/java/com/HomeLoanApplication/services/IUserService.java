package com.HomeLoanApplication.services;

import java.util.List;
import java.util.Optional;

import com.HomeLoanApplication.entites.User;

public interface IUserService {

	public List<User> viewAllUsers();

	public Optional<User> viewUserById(int userId);

	public User addUser(User user);

	public User updateUser(User user);

	public String deleteUser(User user);

	public Optional<User> viewUserByUserName(String userName);

	boolean isValidUser(String userName, String password);

}
