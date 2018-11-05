package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.OptionHelper;

import java.util.*;

@Service("tariffService")
public class TariffServiceImpl extends GenericServiceImpl<TariffPlan, Long>
        implements TariffService {

    @Autowired
    private OptionService optionService;

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionHelper optionHelper;

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
        Set<Option> uniqueOptionsToAdd = new HashSet<>();
        tariffOptions.forEach(
                option -> {
                    uniqueOptionsToAdd.add(option);
                    uniqueOptionsToAdd.addAll(option.getInclusiveOptions());
                }
        );
        List<Option> optionsToAdd = new ArrayList<>(uniqueOptionsToAdd);
        if(optionHelper.existConflicts(optionsToAdd)){
            throw new IllegalArgumentException("Exist options conflicts");
        }
        int parsePrice = Integer.parseInt(price);
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName(name);
        tariffPlan.setPrice(parsePrice);
        tariffPlan.setOptions(optionsToAdd);
        add(tariffPlan);
    }
}
