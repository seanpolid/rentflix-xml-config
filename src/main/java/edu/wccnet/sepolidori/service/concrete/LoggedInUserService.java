package edu.wccnet.sepolidori.service.concrete;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.wccnet.sepolidori.entity.Customer;

@Service
@SessionScope
public class LoggedInUserService {
	
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
