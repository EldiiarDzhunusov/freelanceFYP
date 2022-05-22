package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/admin")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/")
    public String mainPage(Model model){

        return "redirect:/freelancers";
    }

    @GetMapping("/login")
    public String loginPage(Model model, String status){
        return "user/login";
    }

    @GetMapping("/register_freelancer")
    public String showRegistrationFormFreelancer(Model model, String status){
        model.addAttribute("user", new User());
        return "freelancer/registration_form";
    }

    @GetMapping("/register_employer")
    public String showRegistrationFormEmployer(Model model, String status){
        model.addAttribute("user", new User());
        return "employer/registration_form";
    }

    @GetMapping("/register_admin")
    public String showRegistrationFormAdmin(Model model, String status){
        model.addAttribute("user", new User());
        return "admin/registration_form";
    }


}
