package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = {"/skills","/skills/search"})
    public String showSkills(Model model, String keyword){
        List<Skill> listSkills;
        if(keyword!=null){
            listSkills = skillRepository.findByKeyword(keyword);
        }else{
            listSkills= skillRepository.findAll();
        }
        model.addAttribute("listSkills",listSkills);
        return "skill/list";
    }

    @GetMapping("/skills/{id}")
    public String skillDetails(@PathVariable("id") Long id, Model model){
        Skill skill = skillRepository.getById(id);
        model.addAttribute("skill",skill);
        return "skill/details";
    }

    @GetMapping("/skills/edit/{id}")
    public String skillsEdit(@PathVariable("id") Long id, Model model){
        Skill skill = skillRepository.getById(id);
        model.addAttribute("skill",skill);
        return "skill/update";
    }

    @PostMapping("/skills/update/{id}")
    public String skillsUpdate(@PathVariable("id") Long id, @Validated Skill skill){
        skillRepository.save(skill);
        return "redirect:/skills";
    }

    @GetMapping("/skills/new")
    public String showNewSkillForm(Model model){
        model.addAttribute("skill", new Skill());
        return "skill/create_form";
    }

    @PostMapping("/skills/create")
    public String proceedNewSkill(@Validated Skill skill){
        skillRepository.save(skill);
        return "redirect:/skills";
    }

    @GetMapping("/skills/delete/{id}")
    public String deleteSkill(@PathVariable("id") Long id){
        Skill skill = skillRepository.getById(id);
        System.out.println(skill.getName());
        skillRepository.delete(skill);
        return "redirect:/skills";
    }
}
