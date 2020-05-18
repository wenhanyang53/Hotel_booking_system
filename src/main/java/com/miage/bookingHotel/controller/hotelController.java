package com.miage.bookingHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hotelController {
    @RequestMapping("/hotel")
    public String hotel(){
        return "hotel";
    }
}
