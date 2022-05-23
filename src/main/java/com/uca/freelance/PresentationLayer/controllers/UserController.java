package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.SkillService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.Role;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    @PostMapping("/process_register_freelancer")
    public String processRegisterFreelancer(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.FREELANCER);
        userService.save(user);
        return "user/register_success";
    }

    @PostMapping("/process_register_employer")
    public String processRegisterEmployer(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.EMPLOYER);
        userService.save(user);
        return "user/register_success";
    }

    @PostMapping("/process_register_admin")
    public String processRegisterAdmin(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.ADMIN);
        userService.save(user);
        return "user/register_success";
    }

    @GetMapping(path = {"/users","/users/search"})
    public String listUsers(Model model, String keyword){
        List<User> userList;
        if(keyword!=null){
            userList = userService.findByKeyword(keyword);
        }else{
            userList = userService.findAll();
        }
        model.addAttribute("listUsers",userList);

        return "user/list";
    }

    @GetMapping("/users/edit/profile/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id: " +id));
        model.addAttribute("user",user);
        return "user/edit";
    }


    @PostMapping("/users/update/profile/{id}")
    public String updateUser(@PathVariable("id") Long id, @Validated User user,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "user/list";
        }

        userService.save(user);

        List<User> userList = userService.findAll();
        model.addAttribute("listUsers",userList);
        return "user/list";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id, Model model){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            userService.deleteById(id);

            return "redirect:/users";
        }else{
            throw new IllegalArgumentException("User do not have permission to delete this user");
        }

    }

    @GetMapping("/users/profile/{id}")
    public String profileInfo(@PathVariable(name = "id") Long id, Model model, Principal principal){

        User user = userService.getById(id);
        User principalUser = userService.findByEmail(principal.getName());

        if(principalUser.getId()==user.getId() || principalUser.getRole()==Role.ADMIN){

            model.addAttribute("user",user);
            if(user.getRole()==Role.EMPLOYER){
                return "employer/details";
            }else{
                return "freelancer/details";
            }

        }
        //think what link should be here
        else{
            return "index";
        }


    }

    @GetMapping("/users/edit/password/{id}")
    public String showUpdatePasswordForm(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userService.findById(id);

        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "user/update_password";
        }
        return "redirect:/users/profile/"+id;

    }

    @PostMapping("/users/update/password/{id}")
    public String updatePassword(@PathVariable(name = "id") Long id, @Validated User user, BindingResult result, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);

        List<User> userList = userService.findAll();
        model.addAttribute("listUsers",userList);
        return "user/list";
    }

    @GetMapping("/users/skills/{id}")
    public String showUserSkills(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            model.addAttribute("skills",user.get().getUserSkills());
            model.addAttribute("user",user.get());
            return "user/skills";
        }
        return "index";
    }

    @GetMapping("/users/edit/skills/{id}")
    public String showUpdateSkillsForm(@PathVariable(name = "id") Long id,Model model){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            model.addAttribute("userSkills", user.get().getUserSkills());
            model.addAttribute("skills",skillService.findAll());
            model.addAttribute("user",user.get());
            return "user/update_skills";
        }
        return "index";
    }
    @PostMapping("/users/update/skills/{id}")
    public String updateUserSkills(@PathVariable(name = "id") Long id, @RequestParam("skills[]") String[] skillId, Model model){
        Collection<Skill> skills = new ArrayList<>();

        for (int i = 0; i < skillId.length; i++) {
            try {
                Long num = Long.parseLong(skillId[i]);
                skills.add(skillService.findById(num).get());
            } catch (NumberFormatException nfe) {
                Skill skill = new Skill(skillId[i]);
                skillService.save(skill);
                skills.add(skill);
            }

        }
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            user.get().setUserSkills(skills);
            userService.save(user.get());
            return "redirect:/users/skills/"+id;
        }
        return "redirect:/users/skills/"+id;
    }




}
