package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Application;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.ApplicationStatus;
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
public class ApplicationController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping(path = "/application/apply/{id}")
    public String applyForProject(@PathVariable("id") Long id, Model model){
        Application application1 = new Application();
        application1.setJobTakeId(id);
        model.addAttribute("application1" , application1);

        return "application/create";
    }

    @PostMapping(path = "/application/create")
    public String createProject(@Validated Application application, Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        application.setUser(user);
        Optional<Job> job = jobRepository.findById(application.getJobTakeId());
        application.setJob(job.get());
        application.setApplicationStatus(ApplicationStatus.PENDING);
        applicationRepository.save(application);
        return "redirect:/applications";

    }

    @GetMapping(path = "/applications")
    public String listOfAllApplications(Model model){
        List<Application> applicationList = applicationRepository.findAll();
        model.addAttribute("applicationList", applicationList);
        return "application/list";
    }
}
