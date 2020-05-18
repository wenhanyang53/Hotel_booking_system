package com.miage.bookingHotel.service;


import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.repository.ItineraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("itineraryService")

public class itineraryServiceImpl implements itineraryService {

    @Autowired
    ItineraryRepository itineraryRepository;


    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    @Override
    public Optional<Itinerary> findByReferenceNumber(String referenceNumber) {
        return itineraryRepository.findById(referenceNumber);
    }

    @Override
    public List<Itinerary> findByCustomer_id(String customer_id) {
        return itineraryRepository.findByCustomer_id(customer_id);
    }

    @Override
    public boolean isItineraryExist(Itinerary itinerary) {
        return findByReferenceNumber(itinerary.getReferenceNumber()).isPresent();
    }

    @Override
    public void saveItinerary(Itinerary itinerary) {
        itineraryRepository.save(itinerary);
    }

    @Override
    public void updateItinerary(String referenceNumber, Itinerary itinerary) {
        deleteItineraryByReferenceNumber(referenceNumber);
        saveItinerary(itinerary);
    }

    @Override
    public void deleteItineraryByReferenceNumber(String referenceNumber) {
        itineraryRepository.deleteById(referenceNumber);
    }

    @Override
    public String generateReference_num(String hotel_name, String customer_id) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        return dateNowStr + hotel_name.substring(0,3) + customer_id.substring(customer_id.length()-3);
    }

}
