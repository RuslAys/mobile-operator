package ru.javaschool.mobileoperator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.utils.JsonConverter;

import java.util.List;

@RestController
@RequestMapping("/rest/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public String allOptions() {
        return JsonConverter.toJsonString(optionService.getAllOptionsWithoutLists());
    }

    @GetMapping("/{id}")
    public String optionById(@PathVariable("id") long id) {
        return JsonConverter.toJsonString(optionService.getOptionWithoutLists(id));
    }

    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    @GetMapping("/contract/inclusive/add")
    public String inclusiveOptionsToAddOnContract(@PathVariable String username,
                                                  @RequestParam("contractId") long contractId,
                                                  @RequestParam("optionIds") List<Long> optionIds) {
        return JsonConverter.toJsonString(optionService.getChildInclusiveOptionsOnContract(contractId, optionIds));
    }

    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    @GetMapping("/contract/inclusive/delete")
    public String inclusiveOptionsToDeleteOnContract(@PathVariable String username,
                                                     @RequestParam("contractId") long contractId,
                                                     @RequestParam("optionIds") List<Long> optionIds) {
        return JsonConverter.toJsonString(optionService.getParentInclusiveOptionsOnContract(contractId, optionIds));
    }

    @PreAuthorize("(#username == authentication.principal.username) or hasRole('ROLE_OPERATOR')")
    @GetMapping("/contract/exclusive")
    public String exclusiveOptionsOnContract(@PathVariable String username,
                                             @RequestParam("contractId") long contractId,
                                             @RequestParam("optionIds") List<Long> optionIds) {
        return JsonConverter.toJsonString(optionService.getExclusiveOptionsOnContract(contractId, optionIds));
    }
}
