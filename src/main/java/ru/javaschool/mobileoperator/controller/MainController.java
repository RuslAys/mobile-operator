package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String printHello(ModelMap modelMap){
//        System.out.println("controller works");
//        User user = new User("u", "p");
//        user.setActive(true);
//        Set<UserRoleEnum> set = new HashSet<>();
//        set.add(UserRoleEnum.USER);
//        set.add(UserRoleEnum.ADMIN);
//        set.add(UserRoleEnum.OPERATOR);
//        user.setRoles(set);
//        userService.add(user);
        modelMap.addAttribute("message", "Hi");
        return "main";
    }
}
