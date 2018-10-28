package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.service.TariffService;

import java.util.List;

@Controller
@RequestMapping("/tariff")
public class TariffController {

    @Autowired
    TariffService tariffService;

    @GetMapping()
    public String tariffPage(Model model){
        return tariffService.getTariffsAndOptions(model);
    }

    @PostMapping("/add")
    public String addTariffPlan(@RequestParam String name,
                                @RequestParam(value = "options",required = false)
                                        List<Long> options){
        return tariffService.addTariff(name, options);
    }
}
