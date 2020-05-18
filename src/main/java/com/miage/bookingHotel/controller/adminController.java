package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Customer;
import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.service.CustomerService;
import com.miage.bookingHotel.service.itineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class adminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    itineraryService itineraryService;

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
    @RequestMapping("/customer_list")
    public String customer_list(Model model){
        List<Customer> customers=customerService.getAllCustomers();
        model.addAttribute("customers",customers);
        return "customer_list";
    }
    @RequestMapping("/Itinerary_list")
    public String Itinerary_list(Model model){
        List<Itinerary> itineraries=itineraryService.getAllItineraries();
        model.addAttribute("itineraries",itineraries);
        return "Itinerary_list";
    }
    @GetMapping("/itinerary/{referenceNumber}")
    public String update_Itinerary(@PathVariable("referenceNumber")String referenceNumber, Model model){
        Optional<Itinerary> itinerary=itineraryService.findByReferenceNumber(referenceNumber);
        model.addAttribute("itinerary",itinerary);
        System.out.println("test");
        return "update_itinerary";
    }
    @PostMapping("/updateItinerary")
    public String toUpdate_itinerary(Itinerary itinerary){
        itineraryService.updateItinerary(itinerary.getReferenceNumber(),itinerary);
        return "redirect:/Itinerary_list";
    }
	@DeleteMapping("/deleteItinerary/{referenceNumber}")
    public String toDelete_itinerary(@PathVariable("referenceNumber")String referenceNumber){
        itineraryService.deleteItineraryByReferenceNumber(referenceNumber);
        return "redirect:/Itinerary_list";
    }

}

