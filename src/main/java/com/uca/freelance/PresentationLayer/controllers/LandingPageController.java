package com.uca.freelance.PresentationLayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("LandingPage")
public class LandingPageController {
    @GetMapping
    public String landingPage(){
        return "landingPage";
    }
}
