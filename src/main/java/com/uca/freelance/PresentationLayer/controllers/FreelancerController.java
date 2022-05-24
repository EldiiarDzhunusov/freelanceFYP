package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.BussinessLogicLayer.serviceImplementations.UserService;
import com.uca.freelance.DataAccessLayer.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class FreelancerController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/freelancers")
    public String listUsers(Model model, String keyword, Principal principal){
        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);
        List<User> userList;
        String textForSearch = "";
        if(keyword!=null && !keyword.equals("")){
            textForSearch = "Фрилансеров по запросу " + keyword + " найдено: ";
            userList = userService.findFreelancersByKeyword(keyword);
        }else{
            textForSearch = "Фрилансеров в портале: ";
            userList = userService.findAllFreelancers();
        }
        model.addAttribute("textForSearch",textForSearch);
        model.addAttribute("listUsers",userList);

        return "freelancer/list";
    }

    @GetMapping("freelancer/{id}")
    public String profileInfo(@PathVariable(name = "id") Long id, Model model, Principal principal){
        User currUser = userService.findByEmail(principal.getName());
        model.addAttribute("currUser", currUser);
        Optional<User> user = userService.findById(id);

        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "freelancer/details";
        }else{
            return null;
        }
    }
}
