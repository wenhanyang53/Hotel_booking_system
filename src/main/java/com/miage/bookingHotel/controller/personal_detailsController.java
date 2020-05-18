package com.miage.bookingHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class personal_detailsController {

    @Autowired
    com.miage.bookingHotel.service.itineraryService itineraryService;

    @RequestMapping("/personal_details")
    public String personal_details(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            return "personal_details";
        }
        else{
            model.addAttribute("msg","User name or password error");
            return "login";

        }
    }
}
