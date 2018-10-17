package ru.javaschool.mobileoperator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/admin")
public class AdminPanelController {
    @GetMapping
    public String getAdminPanel(){
        return "admin_panel";
    }
}
