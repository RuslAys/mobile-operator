package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.mobileoperator.service.SaleService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/sale")
public class SaleController {

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
        return saleService.getPageWithTariffsAndNumbers(model);
    }

    @PostMapping("/confirm")
    public String confirmSale(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("birthDate") Date birthDate,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "street", required = false) String street,
                              @RequestParam(value = "house",required = false) String house,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam("password") String password,
                              @RequestParam("confirmPassword") String confirmPassword,
                              @RequestParam("tariff") Long tariffId,
                              @RequestParam("number") Long numberId){
        return saleService.saleContract(firstName,
                lastName, birthDate, city, street, house,
                email, password, confirmPassword, tariffId, numberId);
    }
}
