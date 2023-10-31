package com.HomeLoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApplication.entites.Customer;
import com.HomeLoanApplication.exceptions.CustomerNotFoundException;
import com.HomeLoanApplication.repositories.ICustomerRepository;
import com.HomeLoanApplication.repositories.IUserRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	IUserRepository userRepository;

	@Override
	public String addCustomer(Customer customer) {
		System.out.println(userRepository.findByUserName(customer.getUserName()).isEmpty());
		if (userRepository.findByUserName(customer.getUserName()).isEmpty()) {
			if (customerRepository.findByAadharNumber(customer.getAadharNumber()).isEmpty()) {
				if (customerRepository.findByPanNumber(customer.getPanNumber()).isEmpty()) {
					customerRepository.save(customer);
					return "Customer Added Successfully: " + customer.toString();
				} else {
					return "PanNumber Already exist for another account";
				}

			} else {
				return "AdharNumber Already exist for another account";
			}
		} else {
			return "UserName Already exist for another account";
		}

	}

	@Override
	public Customer viewCustomer(int custID) {
		if (customerRepository.findById(custID).isPresent()) {
			return customerRepository.findById(custID).get();
		} else {
			throw new CustomerNotFoundException("Customer Not Found for Id:" + custID);
		}
	}

	@Override
	public Customer viewCustomerByUserName(String userName) {

		if (customerRepository.findByUserName(userName).isPresent()) {
			return customerRepository.findByUserName(userName).get();
		} else {
			throw new CustomerNotFoundException("Customer Not Found for UserName:" + userName);
		}
	}

	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new CustomerNotFoundException("No Customers present in the database!");
		} else {
			return customerRepository.findAll();
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		if (customerRepository.findById(customer.getUserId()).isPresent()) {
			return customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException("Customer not found for Id: " + customer.getUserId());
		}
	}

	@Override
	public String deleteCustomer(int userId) {

		if (customerRepository.findById(userId).isPresent()) {
			customerRepository.deleteById(userId);
			return "Customer deleted Successfully for Id:" + userId;
		} else {
			throw new CustomerNotFoundException("Customer not found for Id" + userId);
		}
	}

	@Override
	public boolean isValidCustomer(String userName, String password) {

		if (customerRepository.findByUserNameAndPassword(userName, password).isPresent())
			return true;
		else
			throw new CustomerNotFoundException("UserName or Password Invalid");
	}

}
