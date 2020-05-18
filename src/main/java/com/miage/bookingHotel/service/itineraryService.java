package com.miage.bookingHotel.service;
import com.miage.bookingHotel.model.Itinerary;
import java.util.List;
import java.util.Optional;

public interface itineraryService {
    List<Itinerary> getAllItineraries();
    Optional<Itinerary> findByReferenceNumber(String referenceNumber);
    List<Itinerary> findByCustomer_id(String customer_id);
    boolean isItineraryExist(Itinerary itinerary);
    void saveItinerary(Itinerary itinerary);
    void updateItinerary(String referenceNumber, Itinerary itinerary);
    void deleteItineraryByReferenceNumber(String referenceNumber);
    String generateReference_num(String hotel_name, String last_name);
}
