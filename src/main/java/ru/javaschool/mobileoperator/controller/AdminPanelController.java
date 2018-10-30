package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.api.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminPanelController {
    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private LockService lockService;

    @GetMapping
    public String adminPage(Model model) {
        return "admin";
    }

    @GetMapping("/operator")
    public String admin(Model model){
        List<User> operatorList = userService.findAll(UserRoleEnum.OPERATOR);
        model.addAttribute("operators", operatorList);
        return "operator";
    }

    @PostMapping("/operator/add")
    public String addOperator(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ){
        if(StringUtils.isEmpty(username)){
            model.addAttribute("message", "username is empty");
        }else if(StringUtils.isEmpty(password) || !password.equals(confirmPassword)){
            model.addAttribute("message", "passwords are not equal");
        }else {
            userService.addOperator(username, password);
        }
        return "redirect:/admin/operator";
    }

    @GetMapping("/operator/{username}")
    public String userEditForm(@PathVariable String username,
                               Model model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "operatorEditor";
    }

    @PostMapping("/operator/editOperator")
    public String editOperator(@RequestParam String username,
                               @RequestParam(value = "enabled",
                                       required = false,
                                       defaultValue = "false") Boolean enabled){
        userService.setOperatorActiveStatus(username, enabled);
        return "redirect:/admin/operator";
    }

    @GetMapping("/option")
    public String optionPage(Model model){
        model.addAttribute("options", optionService.findAll());
        return "option";
    }

    @PostMapping("/option/add")
    public String addOption(@RequestParam String name,
                            @RequestParam(value = "inclusiveOptions",required = false)
                                    List<Long> inclusiveOptions,
                            @RequestParam(required = false, value = "exclusiveOptions")
                                    List<Long> exclusiveOptions){
        optionService.addOption(name, inclusiveOptions, exclusiveOptions);
        return "redirect:/admin/option";
    }

    @GetMapping("/tariff")
    public String tariffPage(Model model){
        model.addAttribute("tariffPlans", tariffService.findAll());
        model.addAttribute("options", optionService.findAll());
        return "tariff";
    }

    @PostMapping("/tariff/add")
    public String addTariffPlan(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam(value = "options",required = false)
                                        List<Long> options){
        tariffService.addTariff(name, price, options);
        return "redirect:/admin/tariff";
    }

    @GetMapping("/phone")
    public String phoneNumbersPage(Model model){
        model.addAttribute("numbers", phoneNumberService.getAllNumbers());
        return "phone";
    }

    @PostMapping("/phone/add")
    public String addPhoneNumber(@RequestParam String number){
        phoneNumberService.addNumber(number);
        return "redirect:/admin/phone";
    }

    @GetMapping("lock")
    public String lockPage(Model model){
        model.addAttribute("locks", lockService.findAll());
        return "lock";
    }

    @PostMapping("/lock/add")
    public String addLock(@RequestParam("name") String name,
                          @RequestParam(value = "deletedByUser",
                                  defaultValue = "false",
                                  required = false) Boolean deletedByUser){
        lockService.addLock(name, deletedByUser);
        return "redirect:/admin/lock";
    }

}
