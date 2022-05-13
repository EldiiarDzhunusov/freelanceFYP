package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Skill2Controller {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping(path = "/skills2")
    public String listUsers(Model model, String keyword){
        List<Skill> skillList;
        String textForSearch = "";
        if(keyword!=null && !keyword.equals("")){
            textForSearch = "Навыков по запросу " + keyword + " найдено: ";
            skillList = skillRepository.findByKeyword(keyword);
        }else{
            textForSearch = "Всего навыков: ";
            skillList = skillRepository.findAll();
        }
        model.addAttribute("textForSearch",textForSearch);
        model.addAttribute("skillList",skillList);

        return "skill2_list";
    }
}
