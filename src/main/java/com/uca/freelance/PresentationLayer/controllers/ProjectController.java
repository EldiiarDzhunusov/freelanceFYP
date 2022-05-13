package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private JobRepository jobRepository;


    @GetMapping(path = "/projects")
    public String listUsers(Model model, String keyword){
        List<Job> jobList;
        String textForSearch = "";
        if(keyword!=null && !keyword.equals("")){
            textForSearch = "Работ по запросу " + keyword + " найдено: ";
            jobList = jobRepository.findByKeyword(keyword);
        }else{
            textForSearch = "Всего работ : ";
            jobList = jobRepository.findAll();
        }
        model.addAttribute("textForSearch",textForSearch);
        model.addAttribute("listJobs",jobList);

        return "project_list";
    }
}
