package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/")
    public String mainPage(Model model, Principal principal){
        if(principal==null){
            return "redirect:/login";
        }

        User currUser = userService.findByEmail(principal.getName());

        model.addAttribute("currUser", currUser);
        if(userService.findByEmail(principal.getName()).getRole()== Role.EMPLOYER){
            return "redirect:/freelancers";
        }else{
            return "redirect:/projects";
        }

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
