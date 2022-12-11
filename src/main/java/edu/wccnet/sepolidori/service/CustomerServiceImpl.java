package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.CustomerDAO;
import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.exception_handling.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerDAO customerDAO;
	
	public CustomerServiceImpl(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		Customer customer = customerDAO.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer" + customerId + " not found.");
		}
		return customer;
	}

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
	}

}
