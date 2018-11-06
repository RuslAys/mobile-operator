package ru.javaschool.mobileoperator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private TerminalDeviceService terminalDeviceService;

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
        TerminalDevice terminalDevice = profileService.getFullCustomerInfoByNumber(username);
        TariffPlan tariffPlan = terminalDevice.getTariffPlan();
        model.addAttribute("terminalDevice", terminalDevice);
        model.addAttribute("tariffPlan", tariffPlan);
        model.addAttribute("options", terminalDevice.getOptions());
        model.addAttribute("freeTariffs", profileService.getTariffsExcept(tariffPlan));
        return "profileTariff";
    }

    @PostMapping("/{username}/tariff/change")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String changeTariff(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                               @RequestParam("newTariffId") Long newTariffId){
        profileService.changeTariff(terminalDeviceId, newTariffId);
        return "redirect:/profile/" + username + "/tariff";
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

    /**
     * Get method for options page
     * @param model ui model
     * @param username profile username
     * @return options page
     */
    @GetMapping("/{username}/option")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profileOptionsPage(Model model, @PathVariable("username") String username){
        TerminalDevice terminalDevice = profileService.getFullCustomerInfoByNumber(username);
        model.addAttribute("terminalDevice", terminalDevice);
        model.addAttribute("options", terminalDevice.getOptions());
        model.addAttribute("freeOptions", optionService.getOptionsNotIn(terminalDevice.getOptions()));
        return "profileOption";
    }

    /**
     * Method to add option on terminal device
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param optionId option id to add
     * @return redirect to profile option page {@link #profileOptionsPage(Model, String)}
     */
    @PostMapping(value = "/{username}/option/add", params = "add")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addOption(@PathVariable("username") String username,
                            @RequestParam("terminalDeviceId") Long terminalDeviceId,
                            @RequestParam("optionId") Long optionId){
        profileService.addOption(terminalDeviceId, optionId);
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Method to add option on terminal device
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param optionId option id to add
     * @return redirect to profile option page {@link #profileOptionsPage(Model, String)}
     */
    @PostMapping(value = "/{username}/option/add", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addOptionToCart(@PathVariable("username") String username,
                                  @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                  @RequestParam("optionId") Long optionId,
                                  HttpSession session){
        TerminalDevice terminalDevice = terminalDeviceService.find(terminalDeviceId);
        Option option = optionService.find(optionId);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        //TODO
        //Реализовать проверку на дубликаты
        CartItem item = cartItemService.createItem(
                id, OperationType.ADD_OPTION, null, option, null, null, terminalDevice);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
        System.out.println((Cart) session.getAttribute("cart"));
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Method to remove option from terminal device
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param optionId option id
     * @return redirect to profile option page {@link #profileOptionsPage(Model, String)}
     */
    @PostMapping("/{username}/option/remove")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeOption(@PathVariable("username") String username,
                            @RequestParam("terminalDeviceId") Long terminalDeviceId,
                            @RequestParam("optionId") Long optionId){
        profileService.removeOption(terminalDeviceId, optionId);
        return "redirect:/profile/" + username + "/option";
    }
}
