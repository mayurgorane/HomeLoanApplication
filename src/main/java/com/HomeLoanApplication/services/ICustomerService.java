package com.HomeLoanApplication.services;

import java.util.List;

import com.HomeLoanApplication.entites.Customer;

public interface ICustomerService {

	public Customer viewCustomer(int custID);

	public List<Customer> viewAllCustomers();

	public String addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public String deleteCustomer(int userId);

	boolean isValidCustomer(String userName, String password);

	Customer viewCustomerByUserName(String userName);

}
