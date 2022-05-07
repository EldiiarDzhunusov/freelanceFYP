package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("skills/{id}")
    public String skillDetails(@PathVariable("id") Long id, Model model){
        Skill skill = skillRepository.getById(id);
        model.addAttribute("skill",skill);
        //show users as well
        return "skill_details";
    }

    @GetMapping("skills/edit/{id}")
    public String skillsEdit(@PathVariable("id") Long id, Model model){
        Skill skill = skillRepository.getById(id);
        model.addAttribute("skill",skill);
        return null;
    }

    @PostMapping("skills/update/{id}")
    public String skillsUpdate(@PathVariable("id") Long id, @Validated Skill skill){
        skillRepository.save(skill);
        return null;
    }

    @GetMapping("skills/new")
    public String showNewSkillForm(Model model){
        model.addAttribute("skill", new Skill());
        return null;
    }

    @PostMapping("skills/create")
    public String proceedNewSkill(@Validated Skill skill){
        skillRepository.save(skill);
        return "redirect:/skills";
    }

    @GetMapping("skills/delete/{id}")
    public String deleteSkill(@PathVariable("id") Long id){
        skillRepository.delete(skillRepository.getById(id));
        return "redirect:/skills";
    }
}
