package ru.javaschool.mobileoperator.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javaschool.mobileoperator.domain.User;

import java.security.Principal;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails user){
        return "index";
    }
}
