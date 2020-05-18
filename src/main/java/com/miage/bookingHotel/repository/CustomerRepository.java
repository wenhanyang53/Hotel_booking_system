package com.miage.bookingHotel.repository;

import com.miage.bookingHotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("select c from Customer c where c.phoneNumber =?1")
    List<Customer> findByPhoneNumber(String phone_number);

}
