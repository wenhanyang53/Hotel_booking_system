/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bookingHotel.service;

import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
    public Optional<Customer> findById(String customer_id) {
        return customerRepository.findById(customer_id);
    }

    @Override
    public List<Customer> findByPhoneNumber(String phone_number) {
		return customerRepository.findByPhoneNumber(phone_number);
	}

    @Override
    public boolean isCustomerExist(Customer customer) {
        return findById(customer.getCustomer_id()).isPresent();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

	@Override
    public void updateCustomer(String customer_id, Customer customer) {
		deleteCustomerById(customer_id);
		saveCustomer(customer);
	}
        
    @Override
    public void deleteCustomerById(String customer_id) {
        customerRepository.deleteById(customer_id);
    }


    @Override
    public String generateCustomer_id(String last_name) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        return dateNowStr + last_name.substring(0,3);
    }


}
