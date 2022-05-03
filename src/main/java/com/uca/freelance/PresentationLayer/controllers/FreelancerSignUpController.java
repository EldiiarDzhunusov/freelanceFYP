package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("user/sign_up")
public class FreelancerSignUpController {
    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "freelancerSignUp";
    }
}
