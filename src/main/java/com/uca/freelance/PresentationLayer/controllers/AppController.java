package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.CustomUserDetailsService;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.CustomUserDetails;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public String ViewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new User());

        return "/signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("listUsers",userList);

        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id: " +id));
        model.addAttribute("user",user);
        return "update-user";
    }


    @PostMapping("users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Validated User user,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "users";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        model.addAttribute("listUsers",userList);
        return "users";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id, Principal principal, Model model){
        User user = userRepository.findByEmail(principal.getName());
        if(user.getId()==id){
            userRepository.deleteById(id);
            List<User> userList = userRepository.findAll();
            model.addAttribute("listUsers",userList);
            return "users";
        }else{
            throw new IllegalArgumentException("User do not have permission to delete this user");
        }

    }

    @GetMapping("users/profile")
    public String profileInfo(Principal principal, Model model){
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user",user);
        return "profile";
    }
}
