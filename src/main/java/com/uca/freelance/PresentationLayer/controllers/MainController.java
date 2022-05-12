package com.uca.freelance.PresentationLayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/admin")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/")
    public String mainPage(){
        return "/main_page";
    }
}
