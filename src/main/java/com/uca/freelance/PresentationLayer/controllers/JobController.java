package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.ApplicationService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.JobService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.SkillService;
import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.Application;
import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.models.JobStatus;
import com.uca.freelance.DataAccessLayer.repositories.JobRepository;
import com.uca.freelance.DataAccessLayer.repositories.SkillRepository;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(path = {"/jobs","/jobs/search"})
    public String showAllJobs(Model model, Principal principal, String keyword){
        List<Job> jobs;
        if(keyword!=null){
            jobs = jobService.findByKeyword(keyword);
        }else{
            jobs = jobService.findAll();
        }
//        List<Job> jobs = jobRepository.findAllUnstartedJobs();

        Long userId = userService.findByEmail(principal.getName()).getId();
        model.addAttribute("jobs", jobs);
        model.addAttribute("userId", userId);
        return "job/list";
    }

    @GetMapping("/jobs/{id}")
    public String jobDetails(@PathVariable("id") Long id, Model model){
        Optional<Job> job = jobService.findById(id);
        if(job.isPresent()){
            Optional<User> user = userService.findById(job.get().getAuthorIdToFindEntity());

            model.addAttribute("user",user.get());
            model.addAttribute("job",job.get());
            return "job/details";
        }
        //raise error
        return null;
    }

    @GetMapping("/jobs/delete/{id}")
    public String jobDelete(@PathVariable("id") Long id){
        jobService.deleteById(id);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/new/{id}")
    public String showNewJobForm(@PathVariable Long id, Model model){
        Job job = new Job();
        job.setAuthorIdToFindEntity(id);
        model.addAttribute("job",job);
        model.addAttribute("skills",skillService.findAll());
        return "job/create_form";
    }

    @PostMapping("/jobs/create")
    public String createJob(@Validated Job job, @RequestParam("skills[]") String[] skillId){
        job.setJobStatus(JobStatus.PENDING);
        List<Skill> skills = new ArrayList<>();

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
        //for some reason the freelancer cannot be null
        job.setEmployer(userService.getById(job.getAuthorIdToFindEntity()));
        job.setJobSkills(skills);
        jobService.save(job);
        return "redirect:/jobs";
    }
    //update

    @GetMapping("/jobs/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model){
        Optional<Job> job = jobService.findById(id);
        if(job.isPresent()) {
            List<Skill> skills = skillService.findAll();
            model.addAttribute("job",job.get());
            model.addAttribute("skills",skills);
        }
        return "job/edit";
    }

    @PostMapping("/jobs/update/{id}")
    public String updateJob(@PathVariable("id") Long id, @Validated Job job, @RequestParam("skills[]") String[] skillId){

        List<Skill> skills = new ArrayList<>();
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
        job.setJobSkills(skills);
        jobService.save(job);

        return "redirect:/jobs/"+id;
    }

}
