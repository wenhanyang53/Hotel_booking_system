/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.bookingHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miage.bookingHotel.model.Itinerary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author ethanyang
 */
@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, String> {

    @Query("select i from Itinerary i where i.customer_id =?1")
    List<Itinerary> findByCustomer_id(String Customer_id);

}
