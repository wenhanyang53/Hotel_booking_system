package com.miage.bookingHotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class loginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
