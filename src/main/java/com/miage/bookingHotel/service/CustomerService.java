/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.bookingHotel.service;

import com.miage.bookingHotel.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RA234587
 */

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> findById(String customer_id);
    List<Customer> findByPhoneNumber(String name);
    boolean isCustomerExist(Customer customer);
    void saveCustomer(Customer customer);
    void updateCustomer(String Customer_id, Customer customer);
    void deleteCustomerById(String id);
    String generateCustomer_id(String last_name);

}
