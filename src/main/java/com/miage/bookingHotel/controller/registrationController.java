package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.service.itineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class registrationController {

    @RequestMapping("/registration")
    public String registration(){
        return "registration";
    }
}
