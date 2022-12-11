package edu.wccnet.sepolidori.service;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Customer;

@Service
public class LoginProcessor {
	
	private final LoggedInUserService loggedInUserService;
	private final CustomerService customerService;
	
	public LoginProcessor(CustomerService customerService, LoggedInUserService loggedInUserService) {
		this.loggedInUserService = loggedInUserService;
		this.customerService = customerService;
	}
	
	public Customer login(int customerId) {
		Customer customer;
		switch (customerId) {
		case -1:
			// If the customer is actually the admin
			customer = new Customer();
			customer.setId(-1);
			customer.setProfileImg("astronaught");
			loggedInUserService.setCustomer(customer);
			break;
		default:
			customer = customerService.getCustomer(customerId);
			loggedInUserService.setCustomer(customer);
			break;
		}
		return customer;
	}
}
