package com.vcube.customerportal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.customerportal.Entity.Customer;
import com.vcube.customerportal.Repo.CustomerRepo;
//HOW TO DO 
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public Customer createCustomerData(Customer c) {
		return customerRepo.save(c);
	}

	@Override
	public List<Customer> getAllCustomerInfo() {
		return customerRepo.findAll();// select * from Customer;
	}

	@Override
	public Customer getCustomer(Integer id) {
		return customerRepo.findById(id).get();// select * from Customer where cid = 1;
	}

	@Override
	public Customer updateData(Customer c,Integer id) {
		
		Customer customerFromDB  =  getCustomer(id);
		
		customerFromDB.setAge(c.getAge());
		customerFromDB.setCity(c.getCity());
		customerFromDB.setEmail(c.getEmail());
		customerFromDB.setName(c.getName());
		customerFromDB.setPhone(c.getPhone());
		customerFromDB.setState(c.getState());
		
		return customerRepo.save(customerFromDB);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepo.deleteById(id);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

}