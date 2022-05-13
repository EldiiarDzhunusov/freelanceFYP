package com.uca.freelance.PresentationLayer.controllers;

import com.uca.freelance.DataAccessLayer.entities.User;
import com.uca.freelance.DataAccessLayer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class Freelancers {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/freelancers")
    public String listUsers(Model model, String keyword){
        List<User> userList;
        String textForSearch = "";
        if(keyword!=null && !keyword.equals("")){
            textForSearch = "Фрилансеров по запросу " + keyword + " найдено: ";
            userList = userRepository.findByKeyword(keyword);
        }else{
            textForSearch = "Фрилансеров в портале: ";
            userList = userRepository.findAll();
        }
        model.addAttribute("textForSearch",textForSearch);
        model.addAttribute("listUsers",userList);

        return "freelancers_list";
    }

    @GetMapping("/{id}")
    public String profileInfo(@PathVariable(name = "id") Long id, Model model){

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "freelancers_details";
        }else{
            return "main_page";
        }
    }
}
