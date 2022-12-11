package edu.wccnet.sepolidori.service;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Customer;

@Service
public class LoggedInUserService {
	
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
