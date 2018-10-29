package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.service.PhoneNumberService;
import ru.javaschool.mobileoperator.service.SaleService;
import ru.javaschool.mobileoperator.service.TariffService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    TariffService tariffService;

    @Autowired
    PhoneNumberService phoneNumberService;

    @Autowired
    SaleService saleService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping
    public String salePage(Model model){
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("numbers", phoneNumberService.getAllEmptyNumbers());
        return "sale";
    }

    @PostMapping("/confirm")
    public String confirmSale(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("birthDate") Date birthDate,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "street", required = false) String street,
                              @RequestParam(value = "house",required = false) String house,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "passport", required = false) String passport,
                              @RequestParam("password") String password,
                              @RequestParam("confirmPassword") String confirmPassword,
                              @RequestParam("tariff") Long tariffId,
                              @RequestParam("number") Long numberId){
        saleService.saleContract(firstName,
                lastName, birthDate, city, street, house,
                email, passport, password, confirmPassword, tariffId, numberId);
        return "redirect:/sale";
    }
}
