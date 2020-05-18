package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.model.Itinerary;
import com.miage.bookingHotel.service.itineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class itineraryDetailsController {

    @Autowired
    itineraryService itineraryService;

    @RequestMapping("/itineraryDetails")
    public String itineraryDetails(Model model){
        List<Itinerary> itineraries=itineraryService.getAllItineraries();
        model.addAttribute("itineraries",itineraries);
        return "personal_details";
    }

    @PostMapping("/itinerary")
    @ResponseBody
    public String addItinerary(Itinerary itinerary){
        itineraryService.saveItinerary(itinerary);
        System.out.println(itinerary);
        return "OK";
    }
}
