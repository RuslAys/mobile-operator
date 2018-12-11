package ru.javaschool.mobileoperator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.JsonConverter;

@RestController
@RequestMapping("/rest/tariffs")
public class TariffController {

    @Autowired
    TariffService tariffService;

    @GetMapping
    public String getTariffs(){
        return JsonConverter.toJsonString(tariffService.findAllActualTariffsWithOptions());
    }

    @GetMapping("/{tariffId}")
    public String getTariffWithOptions(@PathVariable(name = "tariffId") Long tariffId){
        return JsonConverter.toJsonString(tariffService.findTariffWithOptions(tariffId));
    }
}
