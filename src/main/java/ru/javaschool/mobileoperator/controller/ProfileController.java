package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;


    @GetMapping("/{username}")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profilePage(Model model, @PathVariable("username") String username){
        model.addAttribute("user", userService.getUser(username));
        model.addAttribute("terminalDevice", profileService.getFullCustomerInfoByNumber(username));
        return "profile";
    }
}
