package ru.javaschool.mobileoperator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class ContractSaleController {
    @GetMapping
    public String saleContractPage(){
        return "contractSale";
    }
}
