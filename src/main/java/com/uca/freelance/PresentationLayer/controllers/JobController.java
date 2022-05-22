package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.Job;
import com.uca.freelance.DataAccessLayer.entities.Skill;
import com.uca.freelance.DataAccessLayer.entities.User;
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
    JobRepository jobRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = {"/jobs","/jobs/search"})
    public String showAllJobs(Model model, Principal principal, String keyword){
        List<Job> jobs;
        if(keyword!=null){
            jobs = jobRepository.findByKeyword(keyword);
        }else{
            jobs = jobRepository.findAll();
        }
//        List<Job> jobs = jobRepository.findAllUnstartedJobs();

        Long userId = userRepository.findByEmail(principal.getName()).getId();
        model.addAttribute("jobs", jobs);
        model.addAttribute("userId", userId);
        return "job/list";
    }

    @GetMapping("/jobs/{id}")
    public String jobDetails(@PathVariable("id") Long id, Model model){
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()){
            Optional<User> user = userRepository.findById(job.get().getAuthorId());
            model.addAttribute("user",user.get());
            model.addAttribute("job",job.get());
            return "job/details";
        }
        //raise error
        return null;
    }

    @GetMapping("/jobs/delete/{id}")
    public String jobDelete(@PathVariable("id") Long id){
        jobRepository.deleteById(id);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/new/{id}")
    public String showNewJobForm(@PathVariable Long id, Model model){
        Job job = new Job();
        job.setAuthorId(id);
        model.addAttribute("job",job);
        model.addAttribute("skills",skillRepository.findAll());
        return "job/create_form";
    }

    @PostMapping("/jobs/create")
    public String createJob(@Validated Job job, @RequestParam("skills[]") String[] skillId){
        job.setStarted(false);
        Collection<Skill> skills = new ArrayList<>();

        for (int i = 0; i < skillId.length; i++) {
            try {
                Long num = Long.parseLong(skillId[i]);
                skills.add(skillRepository.findById(num).get());
            } catch (NumberFormatException nfe) {
                Skill skill = new Skill(skillId[i]);
                skillRepository.save(skill);
                skills.add(skill);
            }

        }

        job.setJobSkills(skills);
        jobRepository.save(job);
        return "redirect:/jobs";
    }
    //update

    @GetMapping("/jobs/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model){
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()) {
            List<Skill> skills = skillRepository.findAll();
            model.addAttribute("job",job.get());
            model.addAttribute("skills",skills);
        }
        return "job/edit";
    }

    @PostMapping("/jobs/update/{id}")
    public String updateJob(@PathVariable("id") Long id, @Validated Job job, @RequestParam("skills[]") String[] skillId){

        Collection<Skill> skills = new ArrayList<>();
        for (int i = 0; i < skillId.length; i++) {
            try {
                Long num = Long.parseLong(skillId[i]);
                skills.add(skillRepository.findById(num).get());
            } catch (NumberFormatException nfe) {
                Skill skill = new Skill(skillId[i]);
                skillRepository.save(skill);
                skills.add(skill);
            }

        }
        job.setJobSkills(skills);
        jobRepository.save(job);

        return "redirect:/jobs/"+id;
    }

}
