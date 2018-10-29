package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.repository.GenericDao;
import ru.javaschool.mobileoperator.repository.OptionDao;
import ru.javaschool.mobileoperator.service.OptionService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service("optionService")
public class OptionServiceImpl extends GenericServiceImpl<Option, Long>
        implements OptionService {

    @Autowired
    OptionDao optionDao;

    @Autowired
    public OptionServiceImpl(@Qualifier("optionDaoImpl") GenericDao<Option, Long> genericDao){
        super(genericDao);
        this.optionDao = (OptionDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addOption(String name,
                            List<Long> inclusiveOptions,
                            List<Long> exclusiveOptions) {

        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Option name can`t be empty");
        }
        Option option = new Option();
        option.setName(name);

        List<Option> inOptions = optionDao.getOptions(inclusiveOptions);
        List<Option> exOptions = optionDao.getOptions(exclusiveOptions);

        if(!Collections.disjoint(inOptions, exOptions)){
            throw new IllegalArgumentException("Sets contain common elements");
        }
        if(!inOptions.isEmpty()){
            inOptions.forEach(option1 -> option1.getParentInclusive().add(option));
            option.setInclusiveOptions(new HashSet<>(inOptions));
        }
        if(!exOptions.isEmpty()){
            exOptions.forEach(option1 -> option1.getParentExclusive().add(option));
            option.setExclusiveOptions(new HashSet<>(exOptions));
        }
        add(option);
        return "redirect:/admin/option";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String getAll(Model model) {
        List<Option> options = findAll();
        model.addAttribute("options", options);
        return "option";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Option> getOptionsByIds(List<Long> ids) {
        return optionDao.getOptions(ids);
    }
}
