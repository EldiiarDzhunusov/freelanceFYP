package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.ApplicationService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.JobService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.Application;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.ApplicationStatus;
import com.uca.freelance.DataAccessLayer.models.JobStatus;
import com.uca.freelance.DataAccessLayer.models.Role;
import com.uca.freelance.DataAccessLayer.repositories.ApplicationRepository;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;



    @GetMapping(path = "/application/apply/{id}")
    public String applyForProject(@PathVariable("id") Long id, Model model){
        Application application1 = new Application();
        application1.setJobTakeId(id);
        model.addAttribute("application1" , application1);

        return "application/create";
    }

    @PostMapping(path = "/application/create")
    public String createApplication(@Validated Application application, Principal principal){
        User user = userService.findByEmail(principal.getName());
        application.setFreelancer(user);
        Optional<Job> job = jobService.findById(application.getJobTakeId());
        application.setJob(job.get());
        application.setApplicationStatus(ApplicationStatus.PENDING);
        applicationService.save(application);
        return "redirect:/applications";

    }

    @GetMapping(path = "/applications")
    public String listOfAllApplications(Model model){
        List<Application> applicationList = applicationService.findAll();
        model.addAttribute("applicationList", applicationList);
        return "application/list";
    }

    @GetMapping(path = "/application/{id}")
    public String applicationDetails(@PathVariable("id") Long id, Model model){
        model.addAttribute("application1",applicationService.getById(id));
        return "application/details";
    }

    @GetMapping(path = "/application/approve/{id}")
    public String applicationApprove(@PathVariable("id") Long id, Model model, Principal principal){
        Application application = applicationService.getById(id);
        User currUser = userService.findByEmail(principal.getName());
        if(application.getJob().getEmployer().getEmail().equals(currUser.getEmail()) || currUser.getRole()== Role.ADMIN){
            application.getJob().setJobStatus(JobStatus.STARTED);
            application.getJob().setPrice(application.getProposedPrice());
            application.setApplicationStatus(ApplicationStatus.ACCEPTED);
            application.getJob().setFreelancer(application.getFreelancer());
            applicationService.save(application);
            return "redirect:/application/"+id;
        }
        return null;
    }

    @GetMapping(path = "/application/reject/{id}")
    public String applicationReject(@PathVariable("id") Long id, Model model, Principal principal){
        Application application = applicationService.getById(id);
        User currUser = userService.findByEmail(principal.getName());
        if(application.getJob().getEmployer().getEmail().equals(currUser.getEmail()) || currUser.getRole()== Role.ADMIN){
            application.setApplicationStatus(ApplicationStatus.REJECTED);
            applicationService.save(application);
            return "redirect:/application/"+id;
        }
        return null;
    }

    @GetMapping(path = "/application/delete/{id}")
    public String applicationDelete(@PathVariable("id") Long id){
        try {
            System.out.println(id+2);
            applicationService.deleteById(id);
        }catch (Exception e){
            System.out.println("DELETION ERROR");
        }
        return "redirect:/applications";
    }


}
