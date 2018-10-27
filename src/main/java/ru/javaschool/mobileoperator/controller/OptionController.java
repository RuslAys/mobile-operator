package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.service.OptionService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @GetMapping
    public String optionPage(Model model){
        return optionService.getAll(model);
    }

    @PostMapping("/add")
    public String addOption(@RequestParam String name,
                            @RequestParam(value = "inclusiveOptions",required = false)
                                    List<Long> inclusiveOptions,
                            @RequestParam(required = false, value = "exclusiveOptions")
                                    List<Long> exclusiveOptions){
        return optionService.addOption(name, inclusiveOptions, exclusiveOptions);
    }
}
