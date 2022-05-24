package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.SkillService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.Role;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @GetMapping(path = {"/skills","/skills/search"})
    public String showSkills(Model model, String keyword, Principal principal){
        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);

        List<Skill> listSkills;
        if(keyword!=null){
            listSkills = skillService.findByKeyword(keyword);
        }else{
            listSkills= skillService.findAll();
        }
        model.addAttribute("listSkills",listSkills);
        return "skill/list";
    }

    @GetMapping("/skills/{id}")
    public String skillDetails(@PathVariable("id") Long id, Model model, Principal principal){

        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);

        Skill skill = skillService.getById(id);
        model.addAttribute("skill",skill);
        User user = userService.findByEmail(principal.getName());
        boolean isAdmin = user.getRole().equals(Role.ADMIN);
        boolean isFreelancer = user.getRole().equals(Role.FREELANCER);
        boolean isEmployer = user.getRole().equals(Role.EMPLOYER);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isFreelancer",isFreelancer);
        model.addAttribute("isEmployer", isEmployer);
        return "skill/details";
    }

    @GetMapping("/skills/edit/{id}")
    public String skillsEdit(@PathVariable("id") Long id, Model model, Principal principal){
        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);

        Skill skill = skillService.getById(id);
        model.addAttribute("skill",skill);
        return "skill/update";
    }

    @PostMapping("/skills/update/{id}")
    public String skillsUpdate(@PathVariable("id") Long id, @Validated Skill skill){
        skillService.save(skill);
        return "redirect:/skills";
    }

    @GetMapping("/skills/new")
    public String showNewSkillForm(Model model, Principal principal){

        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);

        model.addAttribute("skill", new Skill());
        return "skill/create_form";
    }

    @PostMapping("/skills/create")
    public String proceedNewSkill(@Validated Skill skill){
        skillService.save(skill);
        return "redirect:/skills";
    }

    @GetMapping("/skills/delete/{id}")
    public String deleteSkill(@PathVariable("id") Long id){


        skillService.deleteById(id);
        return "redirect:/skills";
    }
}
