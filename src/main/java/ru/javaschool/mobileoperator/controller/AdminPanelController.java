package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.dto.OptionDto;
import ru.javaschool.mobileoperator.domain.dto.PhoneNumberDto;
import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.TariffJmsService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@SuppressWarnings("uncheked")
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
    private TariffJmsService tariffJmsService;

    @Autowired
    private PhoneNumberService phoneNumberService;


    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private Cart cart;

    /**
     * Get method for admin page
     *
     * @param model ui model
     * @return admin page
     */
    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("cart", cart);
        return "admin";
    }

    /**
     * Get method for operator page in admin
     *
     * @param model ui model
     * @return operator page
     */
    @GetMapping(value = "/operator")
    public String operatorPage(Model model) {
        model.addAttribute("operators", userService.findAll(UserRoleEnum.OPERATOR));
        return "operator";
    }

    /**
     * Post method to add operator
     *
     * @param username        operator username
     * @param password        operator password
     * @param confirmPassword string for password confirming
     * @param model           ui model
     * @return redirect to operator admin page {@link #operatorPage(Model)} }
     */
    @PostMapping("/operator/add")
    public String addOperator(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("message", "username is empty");
        } else if (StringUtils.isEmpty(password) || !password.equals(confirmPassword)) {
            model.addAttribute("message", "passwords are not equal");
        } else {
            userService.addOperator(username, password);
        }
        return "redirect:/admin/operator";
    }

    /**
     * Get method to operator editor page
     *
     * @param username operator username
     * @param model    ui model
     * @return operator editor page
     */
    @GetMapping("/operator/{username}")
    public String operatorEditorPage(@PathVariable String username,
                                     Model model) {
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "operatorEditor";
    }

    /**
     * Post method to edit operator
     *
     * @param username operator username
     * @param enabled  operator status
     * @return redirect to operator editor page {@link #operatorEditorPage(String, Model)}
     */
    @PostMapping("/operator/editOperator")
    public String editOperator(@RequestParam String username,
                               @RequestParam(value = "enabled",
                                       required = false,
                                       defaultValue = "false") Boolean enabled) {
        userService.setOperatorActiveStatus(username, enabled);
        return "redirect:/admin/operator";
    }

    /**
     * Get method to options page
     *
     * @param model ui model
     * @return options page
     */
    @GetMapping(value = "/option")
    public String optionPage(Model model) {
        model.addAttribute("options", optionService.findAll());
        return "option";
    }

    /**
     * Post method to add option
     *
     * @param name             option name
     * @param inclusiveOptions inclusive options to option
     * @param exclusiveOptions exclusive options to option
     * @return redirect to options page {@link #operatorPage(Model)}
     */
    @PostMapping("/option/add")
    public String addOption(@RequestParam String name,
                            @RequestParam String price,
                            @RequestParam String connectionCost,
                            @RequestParam(value = "inclusiveOptions", required = false)
                                    List<Long> inclusiveOptions,
                            @RequestParam(required = false, value = "exclusiveOptions")
                                    List<Long> exclusiveOptions) {
        optionService.createOption(name, price, connectionCost, inclusiveOptions, exclusiveOptions);
        return "redirect:/admin/option";
    }

    /**
     * Get method to option editor
     *
     * @return redirect to options page {@link #optionsEditor(Long, Model)}
     */
    @GetMapping("/option/{optionId}")
    public String optionsEditor(@PathVariable Long optionId, Model model) {
        OptionDto option = optionService.findOptionWithListsById(optionId);
        if (option == null) {
            model.addAttribute("error", "not found");
            return "optionEditor";
        }
        model.addAttribute("option", option);
        return "optionEditor";
    }

    /**
     * Get method to tariff page
     *
     * @param model ui model
     * @return tariff page
     */
    @GetMapping(value = "/tariff")
    public String tariffPage(Model model) {
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("options", optionService.findAll());
        return "tariff";
    }

    /**
     * Post method to add tariff
     *
     * @param tariffPlanDto valid tariff plan dto
     * @param optionIds     tariff options
     * @return redirect to tariff page {@link #tariffPage(Model)}}
     */
    @PostMapping("/tariff/add")
    public String addTariffPlan(@Valid TariffPlanDto tariffPlanDto,
                                @RequestParam(value = "optionIds", required = false) List<Long> optionIds,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "errors/bad_request";
        }
        tariffJmsService.addTariff(tariffPlanDto, optionIds);
        return "redirect:/admin/tariff";
    }

    @PostMapping("/tariff/remove")
    public String removeTariffPlan(@RequestParam("tariffId") Long tariffId) {
        tariffJmsService.removeTariff(tariffId);
        return "redirect:/admin/tariff";
    }

    /**
     * Get method to tariff editor page
     *
     * @param model    ui model
     * @param tariffId tariff plan id
     * @return tariff plan editor page
     */
    @GetMapping("/tariff/{id}")
    public String tariffEditorPage(Model model,
                                   @PathVariable("id") Long tariffId) {
        TariffPlanDto tariffPlan = tariffService.findTariffWithOptions(tariffId);
        model.addAttribute("tariff", tariffPlan);
        model.addAttribute("freeOptions", optionService.getOptionsNotIn(tariffPlan.getOptions()));
        return "tariffEditor";
    }

    /**
     * Post method to remove option from tariff
     *
     * @param model    ui model
     * @param tariffId tariff id
     * @return redirect to tariff editor page {@link #tariffEditorPage(Model, Long)}
     */
    @PostMapping("/tariff/{id}/remove")
    public String removeOptionFromTariff(Model model,
                                         @PathVariable("id") Long tariffId,
                                         @RequestParam("optionId") Long optionId) {
        tariffJmsService.removeOptionFromTariff(tariffId, optionId);
        return "redirect:/admin/tariff/" + tariffId;
    }

    /**
     * Post method to remove option from tariff
     *
     * @param model    ui model
     * @param tariffId tariff id
     * @return
     */
    @PostMapping("/tariff/{id}/add")
    public String addOptionToTariff(Model model,
                                    @PathVariable("id") Long tariffId,
                                    @RequestParam("optionId") Long optionId) {
        tariffJmsService.addOptionToTariff(tariffId, optionId);
        return "redirect:/admin/tariff/" + tariffId;
    }

    /**
     * Get method to phone numbers page
     *
     * @param model ui model
     * @return return phone numbers page
     */
    @GetMapping(value = {"/phone/{type}", "/phone"})
    public String phoneNumbersPage(Model model,
                                   @PathVariable Map<String, String> pathVariablesMap,
                                   HttpServletRequest request) {
        model.addAttribute("phones", phoneNumberService.findAll());
        return "phone";
    }

    /**
     * Post method to add phone number
     *
     * @param number phone number
     * @return redirect to phone numbers page {@link #phoneNumbersPage(Model, Map, HttpServletRequest)}}
     */
    @PostMapping("/phone/add")
    public String addPhoneNumber(@RequestParam String number) {
        phoneNumberService.addNumber(number);
        return "redirect:/admin/phone";
    }
}
