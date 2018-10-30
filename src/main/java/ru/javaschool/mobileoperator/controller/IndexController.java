package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.RoleHelper;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private RoleHelper roleHelper;

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal UserDetails user){
        if(roleHelper.isOnlyUser(user)){
            return "redirect:/profile/" + user.getUsername();
        }
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("username") String username){
        model.addAttribute("user", userService.getUser(username));
        return "index";
    }
}
