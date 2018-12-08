package ru.javaschool.mobileoperator.controller;

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
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;
import ru.javaschool.mobileoperator.service.api.ContractService;
import ru.javaschool.mobileoperator.service.api.CustomerService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContractService contractService;

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
        ContractDto contract = contractService.getContractWithOptions(username);
//        model.addAttribute("cart", cartHelper.getCart(session));
        model.addAttribute("user", userService.getUser(username));
        model.addAttribute("contract", contract);
        if(!roleHelper.isOnlyUser(user)){
            model.addAttribute("anotherContracts", customerService.getCustomerByContract(contract).getContracts());
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
        ContractDto contract = contractService.getContractWithOptions(username);
        TariffPlanDto tariffPlan = contract.getTariffPlan();
        model.addAttribute("contract", contract);
        model.addAttribute("tariffPlan", tariffPlan);
        model.addAttribute("options", contract.getOptions());
        model.addAttribute("freeTariffs", tariffService.getTariffsExcept(tariffPlan));
        return "profileTariff";
    }

    /**
     * Post method to change tariff plan on contract
     * @param model ui model
     * @param username customer username
     * @param contractId contract id
     * @param newTariffId new tariff plan id
     * @param session session
     * @return redirect to {@link #profileTariffPage(Model, String)}
     */
    @PostMapping(value = "/{username}/tariff/change" /*,params = "add_to_cart"*/)
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String changeTariff(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("contractId") Long contractId,
                               @RequestParam("newTariffId") Long newTariffId,
                               HttpSession session){
        profileService.changeTariff(contractId, newTariffId);
//        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/profile/" + username + "/tariff";
    }

//    @PostMapping(value = "/{username}/tariff/change",params = "confirm")
//    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
//    public String changeTariffToCart(Model model,
//                               @PathVariable("username") String username,
//                               @RequestParam("contractId") Long contractId,
//                               @RequestParam("newTariffId") Long newTariffId,
//                               HttpSession session){
//        profileService.changeTariff(contractId, newTariffId);
//        model.addAttribute("cart", cartHelper.getCart(session));
//        return "redirect:/cart";
//    }


    /**
     * Get method for options page
     * @param model ui model
     * @param username profile username
     * @return options page
     */
    @GetMapping("/{username}/option")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String profileOptionsPage(Model model, @PathVariable("username") String username){
        ContractDto contract = contractService.getContractWithOptions(username);
        model.addAttribute("contract", contract);
        model.addAttribute("options", contract.getOptions());
        model.addAttribute("freeOptions", optionService.getOptionsNotIn(contract.getOptions()));
        return "profileOption";
    }

    /**
     * Method to add option on terminal device
     * @param username profile username
     * @param contractId terminal device id
     * @param optionId option id to add
     * @return redirect to profile option page {@link #profileOptionsPage(Model, String)}
     */
    @PostMapping(value = "/{username}/option/add", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addOption(Model model,
                            @PathVariable("username") String username,
                            @RequestParam("contractId") Long contractId,
                            @RequestParam("optionId") Long optionId,
                            HttpSession session){
        optionService.addOptionToContract(contractId, optionId);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/cart";
    }

    /**
     * Method to add option on terminal device
     * @param username profile username
     * @param contractId terminal device id
     * @param optionId option id to add
     * @return redirect to profile option page {@link #profileOptionsPage(Model, String)}
     */
    @PostMapping(value = "/{username}/option/add", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String addOptionToCart(Model model,
                                  @PathVariable("username") String username,
                                  @RequestParam("contractId") Long contractId,
                                  @RequestParam("optionId") Long optionId,
                                  HttpSession session){
        optionService.addOptionToContract(contractId, optionId);
        model.addAttribute("cart", cartHelper.getCart(session));
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Post method for remove option from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param contractId contract id
     * @param optionId option id
     * @param session http session
     * @return redirect to profile option management
     */
    @PostMapping(value = "/{username}/option/remove", params = "add_to_cart")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeOption(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("contractId") Long contractId,
                               @RequestParam("optionId") Long optionId,
                               HttpSession session){
        optionService.removeOptionFromContract(contractId, optionId);
        return "redirect:/profile/" + username + "/option";
    }

    /**
     * Post method for remove option from terminal device. Adding item to cart
     * @param model ui model
     * @param username profile username
     * @param contractId contract id
     * @param optionId option id
     * @param session http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/{username}/option/remove", params = "confirm")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String removeOptionToCart(Model model,
                                     @PathVariable("username") String username,
                                     @RequestParam("contractId") Long contractId,
                                     @RequestParam("optionId") Long optionId,
                                     HttpSession session){
        optionService.removeOptionFromContract(contractId, optionId);
        return "redirect:/cart";
    }

    /**
     * Post method for lock / unlock contract
     * @param model ui model
     * @param username profile username
     * @param contractId contract id
     * @param session http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/{username}/lock")
    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    public String lockContract(Model model,
                               @PathVariable("username") String username,
                               @RequestParam("contractId") Long contractId,
                               @AuthenticationPrincipal UserDetails userDetails,
                               HttpSession session){
        profileService.lockContract(contractId, userDetails);
        return  "redirect:/profile/" + username;
    }
}