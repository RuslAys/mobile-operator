package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.mobileoperator.service.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping
    public String salePage(){
        return "sale";
    }
}
