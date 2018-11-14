package ru.javaschool.mobileoperator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.utils.JsonConverter;

@RestController
@RequestMapping("/rest/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public String allOptions(){
        return JsonConverter.toJsonString(optionService.getAllOptionsWithoutLists());
    }

    @GetMapping("/{id}")
    public String optionById(@PathVariable("id") long id){
        return JsonConverter.toJsonString(optionService.getOptionWithoutList(id));
    }
}
