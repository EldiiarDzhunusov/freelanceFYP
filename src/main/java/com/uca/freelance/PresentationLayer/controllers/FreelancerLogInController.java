package com.uca.freelance.PresentationLayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("FreelancerLogIn")
public class FreelancerLogInController {

    @GetMapping
    public String FreelancerLogInPage(){
        return "freelancerLogIn";
    }

//    @PostMapping
//    public String Freelance
}