package ru.javaschool.mobileoperator.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.service.api.CartSaleService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private final Logger logger = LogManager.getLogger(SaleController.class);

    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CartSaleService cartSaleService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CartHelper cartHelper;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * Get method for sale page
     * @param model ui model
     * @return sale page
     */
    @GetMapping
    public String salePage(Model model, HttpSession session){
        model.addAttribute("cart", cartHelper.getCart(session));
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("numbers", phoneNumberService.getAllEmptyNumbers());
        return "sale";
    }

    /**
     * Post method to sale contract
     * @param firstName user first name
     * @param lastName user last name
     * @param birthDate user birth date
     * @param city user city
     * @param street user street
     * @param house user house
     * @param email user email
     * @param passport user passport
     * @param tariffId choosen tariff id
     * @param numberId choosen number id
     * @return redirect to cart page
     */
    @PostMapping(value = "/confirm", params = "confirm")
    public String confirmSaleToCart(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("birthDate") Date birthDate,
                                    @RequestParam(value = "city", required = false) String city,
                                    @RequestParam(value = "street", required = false) String street,
                                    @RequestParam(value = "house",required = false) String house,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "passport", required = false) String passport,
                                    @RequestParam("tariff") Long tariffId,
                                    @RequestParam("number") Long numberId,
                                    HttpSession session){
        cartSaleService.sale(firstName,
                lastName, birthDate, city, street, house,
                email, passport, tariffId, numberId, session);
        return "redirect:/cart";
    }

    /**
     * Post method to sale contract
     * @param firstName user first name
     * @param lastName user last name
     * @param birthDate user birth date
     * @param city user city
     * @param street user street
     * @param house user house
     * @param email user email
     * @param passport user passport
     * @param tariffId choosen tariff id
     * @param numberId choosen number id
     * @return redirect to sale page {@link #salePage(Model, HttpSession)} (Model)}
     */
    @PostMapping(value = "/confirm", params = "add_to_cart")
    public String confirmSale(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("birthDate") Date birthDate,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "street", required = false) String street,
                              @RequestParam(value = "house",required = false) String house,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "passport", required = false) String passport,
                              @RequestParam("tariff") Long tariffId,
                              @RequestParam("number") Long numberId,
                              HttpSession session){
        cartSaleService.sale(firstName,
                lastName, birthDate, city, street, house,
                email, passport, tariffId, numberId, session);
        return "redirect:/sale";
    }

    /**
     * Post method to sale terminal device to existing personal account
     * @param personalAccountId personal account id
     * @param tariffId tariff id
     * @param numberId phone number id
     * @param session http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/confirmPersonalAccount", params = "confirm")
    public String confirmSaleToExistPersonalAccountToCart(@RequestParam("personalAccountId") Long personalAccountId,
                                                          @RequestParam("tariff") Long tariffId,
                                                          @RequestParam("number") Long numberId,
                                                          HttpSession session){
        cartSaleService.saleToPersonalAccount(personalAccountId, tariffId, numberId, session);
        return "redirect:/cart";
    }

    /**
     * Post method to sale terminal device to existing personal account
     * @param personalAccountId personal account id
     * @param tariffId tariff id
     * @param numberId phone number id
     * @param session http session
     * @return redirect to sale page {@link #salePage(Model, HttpSession)}
     */
    @PostMapping(value = "/confirmPersonalAccount", params = "add_to_cart")
    public String confirmSaleToExistPersonalAccount(@RequestParam("personalAccountId") Long personalAccountId,
                                                    @RequestParam("tariff") Long tariffId,
                                                    @RequestParam("number") Long numberId,
                                                    HttpSession session){
        cartSaleService.saleToPersonalAccount(personalAccountId, tariffId, numberId, session);
        return "redirect:/sale";
    }

    /**
     * Search customer by number
     * @param model ui model
     * @param number phone number
     * @return sale page with filled fields by customer info
     */
    @PostMapping("/search")
    public String searchCustomer(Model model,
                                 @RequestParam("number") String number){
        TerminalDevice terminalDevice;
        try {
            terminalDevice = profileService.getTerminalDeviceWithLocksByNumber(number);
        }catch (Exception e){
            terminalDevice = null;
        }
        if(terminalDevice == null){
            model.addAttribute("message", "Not found");
        }else {
            model.addAttribute("terminalDevice", terminalDevice);
            model.addAttribute("tariffs", tariffService.findAll());
            model.addAttribute("numbers", phoneNumberService.getAllEmptyNumbers());
        }
        return "sale";
    }
}
