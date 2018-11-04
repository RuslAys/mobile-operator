package ru.javaschool.mobileoperator.controller;

import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(AdminPanelController.class);
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

    /**
     * Get method for admin page
     * @param model ui model
     * @return admin page
     */
    @GetMapping
    public String adminPage(Model model) {
        return "admin";
    }

    /**
     * Get method for operator page in admin
     * @param model ui model
     * @return operator page
     */
    @GetMapping("/operator")
    public String operatorPage(Model model){
        model.addAttribute("operators", userService.findAll(UserRoleEnum.OPERATOR));
        return "operator";
    }

    /**
     * Post method to add operator
     * @param username operator username
     * @param password operator password
     * @param confirmPassword string for password confirming
     * @param model ui model
     * @return redirect to operator admin page {@link #operatorPage(Model)}
     */
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

    /**
     * Get method to operator editor page
     * @param username operator username
     * @param model ui model
     * @return operator editor page
     */
    @GetMapping("/operator/{username}")
    public String operatorEditorPage(@PathVariable String username,
                               Model model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "operatorEditor";
    }

    /**
     * Post method to edit operator
     * @param username operator username
     * @param enabled operator status
     * @return redirect to operator editor page {@link #operatorEditorPage(String, Model)}
     */
    @PostMapping("/operator/editOperator")
    public String editOperator(@RequestParam String username,
                               @RequestParam(value = "enabled",
                                       required = false,
                                       defaultValue = "false") Boolean enabled){
        userService.setOperatorActiveStatus(username, enabled);
        return "redirect:/admin/operator";
    }

    /**
     * Get method to options page
     * @param model ui model
     * @return options page
     */
    @GetMapping("/option")
    public String optionPage(Model model){
        model.addAttribute("options", optionService.findAll());
        return "option";
    }

    /**
     * Post method to add option
     * @param name option name
     * @param inclusiveOptions inclusive options to option
     * @param exclusiveOptions exclusive options to option
     * @return redirect to options page {@link #optionPage(Model)}
     */
    @PostMapping("/option/add")
    public String addOption(@RequestParam String name,
                            @RequestParam String price,
                            @RequestParam String connectionCost,
                            @RequestParam(value = "inclusiveOptions",required = false)
                                    List<Long> inclusiveOptions,
                            @RequestParam(required = false, value = "exclusiveOptions")
                                    List<Long> exclusiveOptions){
        optionService.addOption(name, price, connectionCost,inclusiveOptions, exclusiveOptions);
        return "redirect:/admin/option";
    }

    /**
     * Get method to tariff page
     * @param model ui model
     * @return tariff page
     */
    @GetMapping("/tariff")
    public String tariffPage(Model model){
        model.addAttribute("tariffPlans", tariffService.findAll());
        model.addAttribute("options", optionService.findAll());
        return "tariff";
    }

    /**
     * Post method to add tariff
     * @param name tariff name
     * @param price tariff price
     * @param options tariff options
     * @return redirect to tariff page {@link #tariffPage(Model)}
     */
    @PostMapping("/tariff/add")
    public String addTariffPlan(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam(value = "options",required = false)
                                        List<Long> options){
        tariffService.addTariff(name, price, options);
        return "redirect:/admin/tariff";
    }

    /**
     * Get method to phone numbers page
     * @param model ui model
     * @return return phone numbers page
     */
    @GetMapping("/phone")
    public String phoneNumbersPage(Model model){
        model.addAttribute("numbers", phoneNumberService.getAllNumbers());
        return "phone";
    }

    /**
     * Post method to add phone number
     * @param number phone number
     * @return redirect to phone numbers page {@link #phoneNumbersPage(Model)}
     */
    @PostMapping("/phone/add")
    public String addPhoneNumber(@RequestParam String number){
        phoneNumberService.addNumber(number);
        return "redirect:/admin/phone";
    }

    /**
     * Get method to locks page
     * @param model ui model
     * @return locks page
     */
    @GetMapping("lock")
    public String lockPage(Model model){
        model.addAttribute("locks", lockService.findAll());
        return "lock";
    }

    /**
     * Post method to add lock
     * @param name lock name
     * @param deletedByUser can be deleted by user
     * @return redirect to locks page {@link #lockPage(Model)}
     */
    @PostMapping("/lock/add")
    public String addLock(@RequestParam("name") String name,
                          @RequestParam(value = "deletedByUser",
                                  defaultValue = "false",
                                  required = false) Boolean deletedByUser){
        lockService.addLock(name, deletedByUser);
        return "redirect:/admin/lock";
    }

}
