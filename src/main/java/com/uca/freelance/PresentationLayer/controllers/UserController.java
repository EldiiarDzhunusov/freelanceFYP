package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;


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

    @GetMapping("/users/edit/profile/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id: " +id));
        model.addAttribute("user",user);
        return "update_user";
    }


    @PostMapping("users/update/profile/{id}")
    public String updateUser(@PathVariable("id") Long id, @Validated User user,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "users";
        }

        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        model.addAttribute("listUsers",userList);
        return "users";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id, Model model){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            List<User> userList = userRepository.findAll();
            model.addAttribute("listUsers",userList);
            return "users";
        }else{
            throw new IllegalArgumentException("User do not have permission to delete this user");
        }

    }

    @GetMapping("users/profile/{id}")
    public String profileInfo(@PathVariable(name = "id") Long id, Model model){

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "user_details";
        }else{
            return "index";
        }


    }

    @GetMapping("users/edit/password/{id}")
    public String showUpdatePasswordForm(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "update_password";
        }
        return "index";

    }

    @PostMapping("users/update/password/{id}")
    public String updatePassword(@PathVariable(name = "id") Long id, @Validated User user, BindingResult result, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        model.addAttribute("listUsers",userList);
        return "users";
    }

    @GetMapping("users/skills/{id}")
    public String showUserSkills(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            model.addAttribute("skills",user.get().getUserSkills());
            model.addAttribute("user",user.get());
            return "user_skills";
        }
        return "index";
    }

    @GetMapping("users/edit/skills/{id}")
    public String showUpdateSkillsForm(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            model.addAttribute("userSkills", user.get().getUserSkills());
            model.addAttribute("skills",skillRepository.findAll());
            model.addAttribute("user",user.get());
            return "user_update_skills";
        }
        return "index";
    }
    @PostMapping("users/update/skills/{id}")
    public String updateUserSkills(@PathVariable(name = "id") Long id, @RequestParam("skills[]") Long[] skillId, Model model){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Collection<Skill> skills = new ArrayList<>();
            for (int i = 0; i < skillId.length; i++) {
                skills.add(skillRepository.findById(skillId[i]).get());
            }
            user.get().setUserSkills(skills);
            userRepository.save(user.get());
            return "redirect:/users/skills/"+id;
        }
        return "redirect:/users/skills/"+id;
    }


}
