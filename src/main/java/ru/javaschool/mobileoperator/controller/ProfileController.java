package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.mobileoperator.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public String profilePage(Model model, @PathVariable String username){
        model.addAttribute("user", userService.getUser(username));
        return "profile";
    }
}
