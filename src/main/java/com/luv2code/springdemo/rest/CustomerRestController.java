package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the customer service
	@Autowired
	private CustomerService customerService;

	// create get mapping for customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	// create get mapping for single customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer ID not found - " + customerId);
		}

		return customer;
	}

	// add mapping for POST /customers to add a customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {

		// pass the id of 0 into customer. this will ensure a new user is added, and not
		// updated. Even if
		// they pass a json with a customer id to this method
		customer.setId(0);
		customerService.saveCustomer(customer);

		return customer;
	}

	// add mapping for PUT /customers to update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {

		customerService.saveCustomer(customer);
		return customer;
	}

	// create get mapping for single customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer ID not found - " + customerId);
		}
		customerService.deleteCustomer(customerId);

		return "Deleted customer with id="+customerId;
	}

}
