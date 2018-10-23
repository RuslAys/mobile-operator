package ru.javaschool.mobileoperator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Principal principal){
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }

//    @GetMapping("/{username}")
//    public String userEditForm(@PathVariable String username, Model model){
//        return "userEdit";
//    }
}
