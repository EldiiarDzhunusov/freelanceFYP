package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.JobService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.Application;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.ApplicationRepository;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;


    @GetMapping(path = "/projects")
    public String listUsers(Model model, String keyword, Principal principal){
        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);
        List<Job> jobList;
        String textForSearch = "";
        if(keyword!=null && !keyword.equals("")){
            textForSearch = "Работ по запросу " + keyword + " найдено: ";
            jobList = jobService.findByKeyword(keyword);
        }else{
            textForSearch = "Всего работ : ";
            jobList = jobService.findAllUnstartedJobs();
        }


        model.addAttribute("textForSearch",textForSearch);
        model.addAttribute("listJobs",jobList);

        return "project_list";
    }




}
