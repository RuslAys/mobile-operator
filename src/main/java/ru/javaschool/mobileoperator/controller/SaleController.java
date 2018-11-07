package ru.javaschool.mobileoperator.controller;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.service.api.CartSaleService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.service.api.TariffService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(SaleController.class);

    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CartSaleService cartSaleService;

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
    public String salePage(Model model){
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
     * @return redirect to sale page {@link #salePage(Model)}
     */
    @PostMapping("/confirm")
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
}
