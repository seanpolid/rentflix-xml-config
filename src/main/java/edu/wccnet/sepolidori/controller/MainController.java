package edu.wccnet.sepolidori.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.service.CustomerService;
import edu.wccnet.sepolidori.service.LoggedInUserService;
import edu.wccnet.sepolidori.service.LoginProcessor;

@Controller
public class MainController {
	
	private final LoginProcessor loginProcessor;
	private final LoggedInUserService loggedInUserService;
	private final CustomerService customerService;
	
	public MainController(CustomerService customerService, LoggedInUserService loggedInUserService, LoginProcessor loginProcessor) {
		this.loginProcessor = loginProcessor;
		this.customerService = customerService;
		this.loggedInUserService = loggedInUserService;
	}
	
	@RequestMapping({"/home", "/"})
	public String home(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("customer", loggedInUserService.getCustomer());
		return "login";
	}
	
	@RequestMapping("/loginCustomer")
	public String loginCustomer(@RequestParam int customerId, Model model) {
		Customer customer = loginProcessor.login(customerId);
		model.addAttribute("customer", customer);
		return "library";
	}
	
}
