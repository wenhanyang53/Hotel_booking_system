package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.util.CustomErrorType;
import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@Controller
public class CustomerController {

    public static final Logger logger = LoggerFactory.getLogger(com.miage.bookingHotel.controller.CustomerController.class);
    @Autowired
	CustomerService customerService;
    
     //------------------------- [GET]- get All customers ------------------------------------------
	@RequestMapping(value = "/customers", method = RequestMethod.GET,
                produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<ProjectStatus>(new ProjectStatus("No customer found"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}


    //------------------------- [GET] - get customer by ID  ----------------------------------------
        
	@RequestMapping(value = "/customers/{cusotmer_id}", method = RequestMethod.GET,
                produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getCustomer(@PathVariable("cusotmer_id") String id) {
		logger.info("Fetching User with id {}", id);
		Optional<Customer> customer = customerService.findById(id);
		if (!customer.isPresent()) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Customer with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
	}


    //------------------------ [POST] -- add new customer - fn : createCustomer()-------------------

    @RequestMapping(value = "/customers", method = RequestMethod.POST, produces = { "application/json", "application/xml" })
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Customer : {}", customer);
		customer.setCustomer_id(customerService.generateCustomer_id(customer.getLastName()));
		if (customerService.isCustomerExist(customer)) {
			logger.error("Unable to create. A Customer with id {} already exist", customer.getCustomer_id());
			return new ResponseEntity<>(new CustomErrorType("Unable to create. A Customer with customer id " +
			customer.getCustomer_id()+ " already exists."),HttpStatus.CONFLICT);
		}
		customerService.saveCustomer(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{cusotmer_id}").buildAndExpand(customer.getCustomer_id()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	// ------------------- Update a Customer ------------------------------------------------

	@RequestMapping(value = "/customers/{cusotmer_id}", method = RequestMethod.PUT,
                produces = { "application/json", "application/xml" })
	public ResponseEntity<?> updateCustomer(@PathVariable("cusotmer_id") String id, @RequestBody Customer customer) {
		logger.info("Updating Customer with id {}", id);
		Optional<Customer> currentCustomer = customerService.findById(id);
		if (!currentCustomer.isPresent()) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		customerService.updateCustomer(id, customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}



        // ------------------- Delete a Customer-----------------------------------------

	@RequestMapping(value = "/customers/{cusotmer_id}", method = RequestMethod.DELETE,
                produces = { "application/json", "application/xml" })
	public ResponseEntity<?> deleteCustomer(@PathVariable("cusotmer_id") String id) {
		logger.info("Fetching & Deleting User with id {}", id);
		Optional<Customer> customer = customerService.findById(id);
		if (!customer.isPresent()) {
			logger.error("Unable to delete. Customer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		customerService.deleteCustomerById(id);
		return new ResponseEntity<ProjectStatus>(new ProjectStatus("Successfully deleted itinerary with customer id "
				+ id), HttpStatus.NO_CONTENT);
	}

	@RequestMapping("/customerDetails")
	public String getCustomers(Model model){
		List<Customer>customers=customerService.getAllCustomers();
		model.addAttribute("customers",customers);
		return "personal_details";
	}

	// response status
	public class ProjectStatus {
		private String status;

		public ProjectStatus(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}
	}



}