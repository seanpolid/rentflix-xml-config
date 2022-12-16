package edu.wccnet.sepolidori.service.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Customer;

public interface CustomerService {
	
	public Customer getCustomer(int customerId);
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public void deleteCustomer(int customerId);
	
}
