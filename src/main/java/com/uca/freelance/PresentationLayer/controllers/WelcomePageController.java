package com.uca.freelance.PresentationLayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WelcomePageController {

    @GetMapping
    public String WelcomePage(){
        return "welcomePage";
    }
}
