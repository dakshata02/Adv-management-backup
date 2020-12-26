package com.capgemini.advertisement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;
import com.capgemini.advertisement.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	

	//get customer by Id
	//http://localhost:8081/api/customer/1
	@ApiOperation(value = "Get customer by Id",
			response = CustomerMaster.class,tags="get-customer-by-id",
			consumes="custId",
			httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<CustomerMaster> getCustomerById(@PathVariable Integer id){
		try {
			CustomerMaster customer= customerService.getCustomerById(id);
			log.info("Customer added"+ customer);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}catch(CustomerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//get all customer
	//http://localhost:8081/api/customer/
	@ApiOperation(value = "Get all customer",
			response = CustomerMaster.class,
			tags="get-all-customer",
			httpMethod = "GET")
	@GetMapping("/")
	public ResponseEntity<List<CustomerMaster>> getAllCustomer(){
		try {
			List<CustomerMaster> customerList = customerService.getAllCustomer();
			log.info("Returning all customer details");
			return new ResponseEntity<>(customerList,HttpStatus.OK);
		}catch(CustomerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/customer/
	//add customer   
	@ApiOperation(value = "Add Customer",
			consumes = "receives Customer object as request body",
			response =String.class)
	@PostMapping("/")
	public String addCustomer(@RequestBody CustomerMaster customer) {
		try {
			Integer status= customerService.addCustomer(customer);
			if(status ==1) {
				log.info("Customer:"+customer.getCustFirstName()+" added to database");
				return "Customer:"+customer.getCustFirstName()+" added to database";
			}else {
				log.debug("Unable to add customer");
				return "Unable to add customer to database";
			}

		}catch(CustomerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/customer/1
	//delete customer
	@ApiOperation(value = "Delete Customer",
			consumes = "customerId",
			response =String.class)
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		try {
			Integer status= customerService.deleteCustomer(id);
			if(status ==1) {
				log.info("Customer: "+id+" deleted from database");
				return "Customer: "+id+" deleted from database";
			}else {
				log.debug("Unable to delete Customer from database");
				return "Unable to delete Customer from database";
			}

		}catch(CustomerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/customer/
	//update customer
	@PutMapping("/")
	@ApiOperation(value = "Update Customer",
	consumes = "receives Customer object as request body",
	response =CustomerMaster.class)
	public ResponseEntity<CustomerMaster> updateCustomer(@RequestBody CustomerMaster customer) {
		try {
			CustomerMaster updatedCustomer= customerService.updateCustomer(customer);
			log.info("Customer: "+ customer.getCustId()+ " updated");
			return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);

		}catch(CustomerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}



}