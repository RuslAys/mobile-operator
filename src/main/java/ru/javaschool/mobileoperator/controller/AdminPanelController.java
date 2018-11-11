package ru.javaschool.mobileoperator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.api.LockService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("uncheked")
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminPanelController {
    private final Logger logger = LogManager.getLogger(AdminPanelController.class);
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

    @Autowired
    private CartHelper cartHelper;

    /**
     * Get method for admin page
     * @param model ui model
     * @return admin page
     */
    @GetMapping
    public String adminPage(Model model, HttpSession session) {
        model.addAttribute("cart", cartHelper.getCart(session));
        return "admin";
    }

    /**
     * Get method for operator page in admin
     * @param model ui model
     * @return operator page
     */
    @GetMapping(value = {"/operator/l/{type}", "/operator"})
    public String operatorPage(Model model,
                               @PathVariable Map<String, String> pathVariablesMap,
                               HttpServletRequest request){
        PagedListHolder<User> userPagedListHolder = null;
        String type = pathVariablesMap.get("type");
        if(null == type) {
            List<User> operators = (userService.findAll(UserRoleEnum.OPERATOR));
            userPagedListHolder = new PagedListHolder<User>();
            userPagedListHolder.setSource(operators);
            userPagedListHolder.setPageSize(3);
            request.getSession().setAttribute("operators",  userPagedListHolder);
        }else if("next".equals(type)) {
            // Return next set of list
            userPagedListHolder = (PagedListHolder<User>) request.getSession()
                    .getAttribute("operators");
            userPagedListHolder.nextPage();
        }else if("prev".equals(type)) {
            // Return previous set of list
            userPagedListHolder = (PagedListHolder<User>) request.getSession()
                    .getAttribute("operators");
            userPagedListHolder.previousPage();
        }else  {
            // Return specific index set of list
            userPagedListHolder = (PagedListHolder<User>) request.getSession()
                    .getAttribute("operators");
            int pageNum = Integer.parseInt(type);
            userPagedListHolder.setPage(pageNum);
        }
        return "operator";
    }

    /**
     * Post method to add operator
     * @param username operator username
     * @param password operator password
     * @param confirmPassword string for password confirming
     * @param model ui model
     * @return redirect to operator admin page {@link #operatorPage(Model, Map, HttpServletRequest)} }
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
    @GetMapping(value = {"/option/{type}", "/option"})
    public String optionPage(Model model,
                             @PathVariable Map<String, String> pathVariablesMap,
                             HttpServletRequest request){
        PagedListHolder<Option> optionPagedListHolder = null;
        String type = pathVariablesMap.get("type");
        if(null == type) {
            List<Option> options = (optionService.findAll());
            optionPagedListHolder = new PagedListHolder<Option>();
            optionPagedListHolder.setSource(options);
            optionPagedListHolder.setPageSize(3);
            request.getSession().setAttribute("options",  optionPagedListHolder);
        }else if("next".equals(type)) {
            // Return next set of list
            optionPagedListHolder = (PagedListHolder<Option>) request.getSession()
                    .getAttribute("options");
            optionPagedListHolder.nextPage();
        }else if("prev".equals(type)) {
            // Return previous set of list
            optionPagedListHolder = (PagedListHolder<Option>) request.getSession()
                    .getAttribute("options");
            optionPagedListHolder.previousPage();
        }else  {
            // Return specific index set of list
            optionPagedListHolder = (PagedListHolder<Option>) request.getSession()
                    .getAttribute("options");
            int pageNum = Integer.parseInt(type);
            optionPagedListHolder.setPage(pageNum);
        }
        return "option";
    }

    /**
     * Post method to add option
     * @param name option name
     * @param inclusiveOptions inclusive options to option
     * @param exclusiveOptions exclusive options to option
     * @return redirect to options page {@link #operatorPage(Model, Map, HttpServletRequest)}
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
    @GetMapping(value = {"/tariff/l/{type}", "/tariff"})
    public String tariffPage(Model model,
                             @PathVariable Map<String, String> pathVariablesMap,
                             HttpServletRequest request){
        PagedListHolder<TariffPlan> tariffPagedListHolder = null;
        String type = pathVariablesMap.get("type");
        if(null == type) {
            List<TariffPlan> tariffs = (tariffService.findAll());
            tariffPagedListHolder = new PagedListHolder<TariffPlan>();
            tariffPagedListHolder.setSource(tariffs);
            tariffPagedListHolder.setPageSize(3);
            request.getSession().setAttribute("tariffs",  tariffPagedListHolder);
        }else if("next".equals(type)) {
            // Return next set of list
            tariffPagedListHolder = (PagedListHolder<TariffPlan>) request.getSession()
                    .getAttribute("tariffs");
            tariffPagedListHolder.nextPage();
        }else if("prev".equals(type)) {
            // Return previous set of list
            tariffPagedListHolder = (PagedListHolder<TariffPlan>) request.getSession()
                    .getAttribute("tariffs");
            tariffPagedListHolder.previousPage();
        }else  {
            // Return specific index set of list
            tariffPagedListHolder = (PagedListHolder<TariffPlan>) request.getSession()
                    .getAttribute("tariffs");
            int pageNum = Integer.parseInt(type);
            tariffPagedListHolder.setPage(pageNum);
        }
        model.addAttribute("options", optionService.findAll());
        return "tariff";
    }

    /**
     * Post method to add tariff
     * @param name tariff name
     * @param price tariff price
     * @param options tariff options
     * @return redirect to tariff page {@link #tariffPage(Model, Map, HttpServletRequest)}}
     */
    @PostMapping("/tariff/add")
    public String addTariffPlan(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam(value = "options",required = false) List<Long> options){
        tariffService.addTariff(name, price, options);
        return "redirect:/admin/tariff";
    }

    @PostMapping("/tariff/remove")
    public String removeTariffPlan(@RequestParam("tariffId") Long tariffId){
        tariffService.removeTariff(tariffId);
        return "redirect:/admin/tariff";
    }

    /**
     * Get method to tariff editor page
     * @param model ui model
     * @param tariffId tariff plan id
     * @return tariff plan editor page
     */
    @GetMapping("/tariff/{id}")
    public String tariffEditorPage(Model model,
                                    @PathVariable("id") Long tariffId){
        TariffPlan tariffPlan = tariffService.findTariffWithOptions(tariffId);
        model.addAttribute("tariff", tariffPlan);
        model.addAttribute("freeOptions", optionService.getOptionsNotIn(tariffPlan.getOptions()));
        return "tariffEditor";
    }

    /**
     * Post method to remove option from tariff
     * @param model ui model
     * @param tariffId tariff id
     * @return redirect to tariff editor page {@link #tariffEditorPage(Model, Long)}
     */
    @PostMapping("/tariff/{id}/remove")
    public String removeOptionFromTariff(Model model,
                                         @PathVariable("id") Long tariffId,
                                         @RequestParam("optionId") Long optionId){
        tariffService.removeOptionFromTariff(tariffId, optionId);
        return "redirect:/admin/tariff/"+tariffId;
    }

    /**
     * Post method to remove option from tariff
     * @param model ui model
     * @param tariffId tariff id
     * @return
     */
    @PostMapping("/tariff/{id}/add")
    public String addOptionToTariff(Model model,
                                    @PathVariable("id") Long tariffId,
                                    @RequestParam("optionId") Long optionId){
        tariffService.addOptionToTariff(tariffId, optionId);
        return "redirect:/admin/tariff/"+tariffId;
    }

    /**
     * Get method to phone numbers page
     * @param model ui model
     * @return return phone numbers page
     */
    @GetMapping(value = {"/phone/{type}", "/phone"})
    public String phoneNumbersPage(Model model,
                                   @PathVariable Map<String, String> pathVariablesMap,
                                   HttpServletRequest request){
        PagedListHolder<PhoneNumber> phoneNumberPagedListHolder = null;
        String type = pathVariablesMap.get("type");
        if(null == type) {
            List<PhoneNumber> numbers = (phoneNumberService.getAllNumbers());
            phoneNumberPagedListHolder = new PagedListHolder<PhoneNumber>();
            phoneNumberPagedListHolder.setSource(numbers);
            phoneNumberPagedListHolder.setPageSize(3);
            request.getSession().setAttribute("numbers",  phoneNumberPagedListHolder);
        }else if("next".equals(type)) {
            // Return next set of list
            phoneNumberPagedListHolder = (PagedListHolder<PhoneNumber>) request.getSession()
                    .getAttribute("numbers");
            phoneNumberPagedListHolder.nextPage();
        }else if("prev".equals(type)) {
            // Return previous set of list
            phoneNumberPagedListHolder = (PagedListHolder<PhoneNumber>) request.getSession()
                    .getAttribute("numbers");
            phoneNumberPagedListHolder.previousPage();
        }else  {
            // Return specific index set of list
            phoneNumberPagedListHolder = (PagedListHolder<PhoneNumber>) request.getSession()
                    .getAttribute("numbers");
            int pageNum = Integer.parseInt(type);
            phoneNumberPagedListHolder.setPage(pageNum);
        }
        return "phone";
    }

    /**
     * Post method to add phone number
     * @param number phone number
     * @return redirect to phone numbers page {@link #phoneNumbersPage(Model, Map, HttpServletRequest)}}
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
    @GetMapping(value = {"/lock/{type}", "/lock"})
    public String lockPage(Model model,
                           @PathVariable Map<String, String> pathVariablesMap,
                           HttpServletRequest request){
        PagedListHolder<Lock> lockPagedListHolder = null;
        String type = pathVariablesMap.get("type");
        if(null == type) {
            List<Lock> numbers = (lockService.findAll());
            lockPagedListHolder = new PagedListHolder<Lock>();
            lockPagedListHolder.setSource(numbers);
            lockPagedListHolder.setPageSize(3);
            request.getSession().setAttribute("locks",  lockPagedListHolder);
        }else if("next".equals(type)) {
            // Return next set of list
            lockPagedListHolder = (PagedListHolder<Lock>) request.getSession()
                    .getAttribute("locks");
            lockPagedListHolder.nextPage();
        }else if("prev".equals(type)) {
            // Return previous set of list
            lockPagedListHolder = (PagedListHolder<Lock>) request.getSession()
                    .getAttribute("locks");
            lockPagedListHolder.previousPage();
        }else  {
            // Return specific index set of list
            lockPagedListHolder = (PagedListHolder<Lock>) request.getSession()
                    .getAttribute("locks");
            int pageNum = Integer.parseInt(type);
            lockPagedListHolder.setPage(pageNum);
        }
        return "lock";
    }

    /**
     * Post method to add lock
     * @param name lock name
     * @return redirect to locks page {@link #lockPage(Model, Map, HttpServletRequest)}
     */
    @PostMapping("/lock/add")
    public String addLock(@RequestParam("name") String name){
        lockService.addLock(name);
        return "redirect:/admin/lock";
    }

}
