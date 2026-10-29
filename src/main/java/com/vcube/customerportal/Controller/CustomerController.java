package com.vcube.customerportal.Controller;

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

import com.vcube.customerportal.Entity.Customer;
import com.vcube.customerportal.Service.CustomerService;

//http://localhost:9999/customer
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// http://localhost:9999/customer/wel
	@GetMapping("/wel")
	String welcome() {
		return "Welcome to Spring Boot world ";
	}

	// http:localhost:9999/customer/getCus/1
	@GetMapping("/getCus/{cid}")
	Customer getCustomer(@PathVariable Integer cid) {
		return customerService.getCustomer(cid);
	}

	@PutMapping("/update/{cid}")
	Customer updateCustomer(@RequestBody Customer c, @PathVariable Integer cid) {
		return customerService.updateData(c, cid);
	}

	@DeleteMapping("/delete/{cid}")
	String deletCustomer(@PathVariable Integer cid) {
		customerService.deleteCustomer(cid);
		return "Customer " + cid + " Deleted Successfully ";
	}

	@GetMapping("getCus/email/{email}")
	Customer getCustomer(@PathVariable String email) {
		return customerService.findByEmail(email);
	}

	@GetMapping("/getCustomers")
	List<Customer> getAllCustomers() {
		return customerService.getAllCustomerInfo();
	}

	// http://localhost:9999/customer/create
	@PostMapping("/create")
	Customer createCustomerInfo(@RequestBody Customer c) {
		return customerService.createCustomerData(c);
	}

}