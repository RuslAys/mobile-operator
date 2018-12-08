package ru.javaschool.mobileoperator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.mobileoperator.service.api.BillService;
import ru.javaschool.mobileoperator.utils.JsonConverter;

@PreAuthorize("hasRole('ROLE_OPERATOR')")
@RestController
@RequestMapping("/rest/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public String getBillsOnContract(@RequestParam("contractId") long contractId){
        return JsonConverter.toJsonString(billService.getBillsOnContract(contractId));
    }
}
