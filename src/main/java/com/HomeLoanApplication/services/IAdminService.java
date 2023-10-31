package com.HomeLoanApplication.services;

import java.util.List;

import com.HomeLoanApplication.entites.Admin;

public interface IAdminService {

	public List<Admin> viewAllAdmins();

	public Admin viewAdminById(int userId);

	public String addAdmin(Admin admin);

	public String updateAdmin(String userName, Admin admin);

	public String deleteAdmin(int userId);

	boolean isValidAdmin(String userName, String password);

	Admin viewAdminByUserName(String username);

	boolean isAdminPresentByUserNameAndPassword(String userName, String password);

	void updateAdminPassword(String userName, String password);

}
