package ru.javaschool.mobileoperator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartLockService;
import ru.javaschool.mobileoperator.service.api.CartOptionService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.CartTariffService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.PersonalAccountService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final Logger logger = LogManager.getLogger(ProfileController.class);

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

    @Autowired
    private CartOptionService cartOptionService;

    @Autowired
    private CartLockService cartLockService;

    @Autowired
    private CartTariffService cartTariffService;

    @Autowired
    private PersonalAccountService personalAccountService;

    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private RoleHelper roleHelper;


    /**
     * Get method for profile page
     * @param model ui model
     * @param username username to profile
     * @return return profile page
     */
    @GetMapping("/{username}")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profilePage(Model model,
                              @PathVariable("username") String username,
                              @AuthenticationPrincipal UserDetails user,
                              HttpSession session){
        TerminalDevice terminalDevice = profileService.getTerminalDeviceWithLocksByNumber(username);
        model.addAttribute("cart", cartHelper.getCart(session));
        model.addAttribute("user", userService.getUser(username));
        model.addAttribute("terminalDevice", profileService.getTerminalDeviceWithOptions(username));
        model.addAttribute("terminalDeviceLocks", profileService.getTerminalDeviceWithLocksByNumber(username));
        if(!roleHelper.isOnlyUser(user)){
            model.addAttribute("anotherTerminalDevices", personalAccountService.
                    getPersonalAccountWithTerminalDevices(terminalDevice.getPersonalAccount().getId()).getTerminalDevices());
        }
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
        TerminalDevice terminalDevice = profileService.getTerminalDeviceWithOptions(username);
        TariffPlan tariffPlan = terminalDevice.getTariffPlan();
        model.addAttribute("terminalDevice", terminalDevice);
        model.addAttribute("tariffPlan", tariffPlan);
        model.addAttribute("options", terminalDevice.getOptions());
        model.addAttribute("freeTariffs", profileService.getTariffsExcept(tariffPlan));
        return "profileTariff";
    }

    @PostMapping(value = "/{username}/tariff/change",params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String changeTariff(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                               @RequestParam("newTariffId") Long newTariffId,
                               HttpSession session){
        cartTariffService.changeTariffPlan(terminalDeviceId, newTariffId, session);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/profile/" + username + "/tariff";
    }

    @PostMapping(value = "/{username}/tariff/change",params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String changeTariffToCart(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                               @RequestParam("newTariffId") Long newTariffId,
                               HttpSession session){
        cartTariffService.changeTariffPlan(terminalDeviceId, newTariffId, session);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/cart";
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
     * Post method for add lock to terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param lockId lock id
     * @param session http session
     * @param user auth principal
     * @return redirect to cart page
     */
    @PostMapping(value = "/{username}/lock/add", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addLockToTerminalDevice(Model model,
                                          @PathVariable("username") String username,
                                          @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                          @RequestParam("lockId") Long lockId,
                                          HttpSession session,
                                          @AuthenticationPrincipal UserDetails user){
        cartLockService.addLock(terminalDeviceId, lockId, session, user);
        return "redirect:/cart";
    }

    /**
     * Post method for add lock to terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param lockId lock id
     * @param session http session
     * @param user auth principal
     * @return redirect to profile lock management
     */
    @PostMapping(value = "/{username}/lock/add", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addLockToTerminalDeviceToCart(Model model,
                                                @PathVariable("username") String username,
                                                @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                                @RequestParam("lockId") Long lockId,
                                                HttpSession session,
                                                @AuthenticationPrincipal UserDetails user){
        cartLockService.addLock(terminalDeviceId, lockId, session, user);
        return "redirect:/profile/" + username + "/lock";
    }

    /**
     * Post method for remove lock from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param lockId lock id
     * @param user auth principal
     * @param session http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/{username}/lock/remove", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeLockFromTerminalDevice(Model model,
                                               @PathVariable("username") String username,
                                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                               @RequestParam("lockId") Long lockId,
                                               @AuthenticationPrincipal UserDetails user,
                                               HttpSession session){
        cartLockService.removeLock(terminalDeviceId, lockId, session, user);
        return "redirect:/cart";
    }

    /**
     * Post method for remove lock from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param lockId lock id
     * @param user auth principal
     * @param session http session
     * @return redirect to profile lock management
     */
    @PostMapping(value = "/{username}/lock/remove", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeLockFromTerminalDeviceToCart(Model model,
                                                     @PathVariable("username") String username,
                                                     @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                                     @RequestParam("lockId") Long lockId,
                                                     @AuthenticationPrincipal UserDetails user,
                                                     HttpSession session){
        cartLockService.removeLock(terminalDeviceId, lockId, session, user);
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
        TerminalDevice terminalDevice = profileService.getTerminalDeviceWithOptions(username);
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
    @PostMapping(value = "/{username}/option/add", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addOption(Model model,
                            @PathVariable("username") String username,
                            @RequestParam("terminalDeviceId") Long terminalDeviceId,
                            @RequestParam("optionId") Long optionId,
                            HttpSession session){
        cartOptionService.addOption(terminalDeviceId, optionId, session);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/cart";
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
    public String addOptionToCart(Model model,
                                  @PathVariable("username") String username,
                                  @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                  @RequestParam("optionId") Long optionId,
                                  HttpSession session){
        cartOptionService.addOption(terminalDeviceId, optionId, session);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Post method for remove option from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param optionId option id
     * @param session http session
     * @return redirect to profile option management
     */
    @PostMapping(value = "/{username}/option/remove", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeOption(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("terminalDeviceId") Long terminalDeviceId,
                               @RequestParam("optionId") Long optionId,
                               HttpSession session){
        cartOptionService.removeOption(terminalDeviceId, optionId, session);
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Post method for remove option from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param terminalDeviceId terminal device id
     * @param optionId option id
     * @param session http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/{username}/option/remove", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeOptionToCart(Model model,
                                     @PathVariable("username") String username,
                                     @RequestParam("terminalDeviceId") Long terminalDeviceId,
                                     @RequestParam("optionId") Long optionId,
                                     HttpSession session){
        cartOptionService.removeOption(terminalDeviceId, optionId, session);
        return "redirect:/cart";
    }
}
