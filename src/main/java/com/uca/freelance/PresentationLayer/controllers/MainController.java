package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, String status){
        model.addAttribute("user", new User());
        return "signup_form";
    }


}
