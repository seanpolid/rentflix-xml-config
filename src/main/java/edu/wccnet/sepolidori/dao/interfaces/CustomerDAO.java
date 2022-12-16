package edu.wccnet.sepolidori.dao.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Customer;

public interface CustomerDAO {
	
	public Customer getCustomer(int customerId);
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public void deleteCustomer(int id);
	
}
