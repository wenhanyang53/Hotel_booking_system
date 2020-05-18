package com.miage.bookingHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class bookingController {
    @RequestMapping("/booking")
    public String booking(){
        return "booking";
    }
}
