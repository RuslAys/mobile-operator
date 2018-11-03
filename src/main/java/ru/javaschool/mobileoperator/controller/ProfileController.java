package ru.javaschool.mobileoperator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger logger = Logger.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private OptionService optionService;

    /**
     * Get method for profile page
     * @param model ui model
     * @param username username to profile
     * @return return profile page
     */
    @GetMapping("/{username}")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profilePage(Model model, @PathVariable("username") String username){
        model.addAttribute("user", userService.getUser(username));
        model.addAttribute("terminalDevice", profileService.getFullCustomerInfoByNumber(username));
        return "profile";
    }

    /**
     * Get method for user tariff page
     * @param model ui model
     * @param username username to profile
     * @return profile tariff page
     */
    @GetMapping("/{username}/tariff")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profileTariffPage(Model model, @PathVariable("username") String username){
        model.addAttribute("tariffPlan", profileService.getTariffPlanOnTerminalDeviceByNumber(username));
        model.addAttribute("options", profileService.getOptionsOnTerminalDeviceByNumber(username));
        return "profileTariff";
    }
}
