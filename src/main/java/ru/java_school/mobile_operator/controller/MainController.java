package ru.java_school.mobile_operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java_school.mobile_operator.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String printHello(ModelMap modelMap){
        System.out.println("controller works");
        modelMap.addAttribute("message", "Hi");
        return "main";
    }
}
