package com.HomeLoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.Admin;
import com.HomeLoanApplication.exceptions.AdminNotFoundException;
import com.HomeLoanApplication.repositories.IAdminRepository;
import com.HomeLoanApplication.repositories.IUserRepository;

@Service
public class AdminService implements IAdminService {

	@Autowired
	IAdminRepository adminRepository;
	@Autowired
	IUserRepository userRepository;

	@Override
	public String addAdmin(Admin admin) {

		if (userRepository.findByUserName(admin.getUserName()).isEmpty()) {
			if (adminRepository.findByAdminContact(admin.getAdminContact()).isEmpty()) {
				adminRepository.save(admin);
				return "success";
			} else {
				return "contact";
			}
		} else {
			return "username";
		}
	}

	@Override
	public Admin viewAdminById(int userId) {

		if (adminRepository.findById(userId).isEmpty()) {
			return adminRepository.findById(userId).get();
		} else {
			throw new AdminNotFoundException("Admin of Id: " + userId);
		}
	}
	
	@Override 
	public Admin viewAdminByUserName(String username)
	{
		if(adminRepository.findByUserName(username).isPresent())
			return adminRepository.findByUserName(username).get();
		
		throw new AdminNotFoundException("Admin not found for username : " + username);
		
			
	}
	
	@Override 
	public boolean isAdminPresentByUserNameAndPassword(String userName, String password)
	{
		if (adminRepository.findByUserNameAndPassword(userName, password).isPresent())
			return true;
		else
			return false;	
	}


	@Override
	public List<Admin> viewAllAdmins() {
		List<Admin> admins = adminRepository.findAll();
		if (admins.isEmpty()) {
			throw new AdminNotFoundException("No Admins present in the database!");
		} else {
			return adminRepository.findAll();
		}
	}

	@Override
	public String updateAdmin(String userName, Admin uAdmin) {

		if (adminRepository.findByUserName(userName).isPresent()) {
			if(adminRepository.findByAdminContact(uAdmin.getAdminContact()).isEmpty())
			{
				Admin admin=adminRepository.findByUserName(userName).get();
				admin.setAdminName(uAdmin.getAdminName());
				admin.setAdminContact(uAdmin.getAdminContact());
				adminRepository.save(admin);
				return "success";
			}else {
				return "contact";
			}
		} else {
			throw new AdminNotFoundException("Admin not found for Id: " + uAdmin.getUserId());
		}

	}
	
	@Override
	public void updateAdminPassword(String userName, String password) {

				Admin admin=adminRepository.findByUserName(userName).get();
				admin.setPassword(password);
				adminRepository.save(admin);

	}
	
	@Override
	public String deleteAdmin(int userId) {

		if (adminRepository.findById(userId).isPresent()) {
			adminRepository.deleteById(userId);
			return "Admin deleted Successfully for Id:" + userId;
		} else {
			throw new AdminNotFoundException("Admin not found for Id" + userId);
		}
	}

	@Override
	public boolean isValidAdmin(String userName, String password) {
		if (adminRepository.findByUserNameAndPassword(userName, password).isPresent())
			return true;
		else
			throw new AdminNotFoundException("UserName or Password Invalid");

	}
}
