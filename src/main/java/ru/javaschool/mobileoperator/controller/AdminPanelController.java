package ru.javaschool.mobileoperator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {

    @GetMapping
    public String getAdminPanel(){
        return "admin_panel";
    }
}
