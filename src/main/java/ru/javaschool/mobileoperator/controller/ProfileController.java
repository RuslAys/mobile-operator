package ru.javaschool.mobileoperator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;

import java.util.List;

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

    /**
     * Get method for profile lock page
     * @param model ui model
     * @param username username to profle
     * @return profile lock page
     */
    @GetMapping("/{username}/lock")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profileLockPage(Model model, @PathVariable("username") String username){
        TerminalDevice terminalDevice = profileService.getTerminalDeviceWithLocksByNumber(username);
        model.addAttribute("terminalDevice", terminalDevice);
        model.addAttribute("freeLocks", profileService.getLocksNotOnTerminalDevice(terminalDevice));
        return "profileLock";
    }

    /**
     * Post method for add lock to terminal device
     * @param model ui model
     * @param username profile username
     * @param id terminal device id
     * @param ids list of lock ids
     * @return redirect to profile lock page {@link #profileLockPage(Model, String)}
     */
    @PostMapping("/{username}/lock/add")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addLockToTerminalDevice(Model model,
                                          @PathVariable("username") String username,
                                          @RequestParam("terminalDeviceId") Long id,
                                          @RequestParam("freeLocks") List<Long> ids){
        profileService.addLock(id, ids);
        return "redirect:/profile/" + username + "/lock";
    }

    /**
     * Post method for remove lock from terminal device
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param lockId lock id
     * @param user auth principal
     * @return redirect to profile lock page {@link #profileLockPage(Model, String)}
     */
    @PostMapping("/{username}/lock/remove")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeLockFromTerminalDevice(Model model,
                                               @PathVariable("username") String username,
                                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                               @RequestParam("lockId") Long lockId,
                                               @AuthenticationPrincipal UserDetails user){
        profileService.removeLock(user, terminalDeviceId, lockId);
        return "redirect:/profile/" + username + "/lock";
    }

    @GetMapping("/{username}/options")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profileOptionsPage(Model model, @PathVariable("username") String username){
        return "profileOptions";
    }
}
