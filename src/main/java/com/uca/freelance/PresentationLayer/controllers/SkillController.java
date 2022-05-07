package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("skills")
    public String showSkills(Model model){
        List<Skill> listSkills= skillRepository.findAll();
        model.addAttribute("listSkills",listSkills);
        return "skills";
    }
}
