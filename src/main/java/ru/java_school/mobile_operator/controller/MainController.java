package ru.java_school.mobile_operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java_school.mobile_operator.domain.User;
import ru.java_school.mobile_operator.repository.UserDao;
import ru.java_school.mobile_operator.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired(required = true)
    UserService userService;

    @GetMapping("/")
    public String printHello(ModelMap modelMap){
        User user = new User("username", "password");
        userService.add(user);
        modelMap.addAttribute("message", "User saved");
        return "main";
    }
}
