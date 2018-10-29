package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javaschool.mobileoperator.utils.RoleHelper;

@Controller
public class IndexController {

    @Autowired
    RoleHelper roleHelper;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails user){
        if(roleHelper.isOnlyUser(user)){
            return "redirect:/profile/" + user.getUsername();
        }
        return "index";
    }
}
