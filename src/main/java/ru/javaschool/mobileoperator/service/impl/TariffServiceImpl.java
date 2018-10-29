package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.GenericDao;
import ru.javaschool.mobileoperator.repository.TariffDao;
import ru.javaschool.mobileoperator.service.OptionService;
import ru.javaschool.mobileoperator.service.TariffService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service("tariffService")
public class TariffServiceImpl extends GenericServiceImpl<TariffPlan, Long>
        implements TariffService {

    @Autowired
    OptionService optionService;

    @Autowired
    TariffDao tariffDao;

    public TariffServiceImpl() {
    }

    @Autowired
    public TariffServiceImpl(@Qualifier("tariffDaoImpl")GenericDao<TariffPlan, Long> genericDao) {
        super(genericDao);
        this.tariffDao = (TariffDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTariff(String name, String price, List<Long> optionIds) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        List<Option> tariffOptions = optionService.getOptionsByIds(optionIds);
        if(existConflicts(tariffOptions)){
            throw new IllegalArgumentException("Exist options conflicts");
        }
        int parsePrice = Integer.parseInt(price);
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName(name);
        tariffPlan.setPrice(parsePrice);
        tariffPlan.setOptions(new HashSet<>(tariffOptions));
        add(tariffPlan);
    }

    private boolean existConflicts(List<Option> options){
        return options.stream()
                .anyMatch(option -> !Collections.disjoint(options, option.getExclusiveOptions()));
    }
}
