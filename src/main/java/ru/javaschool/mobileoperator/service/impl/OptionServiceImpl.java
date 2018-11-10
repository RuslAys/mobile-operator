package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.repository.api.PersonalAccountDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.exceptions.OptionException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;
import ru.javaschool.mobileoperator.service.exceptions.TerminalDeviceException;
import ru.javaschool.mobileoperator.utils.OptionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("optionService")
public class OptionServiceImpl extends GenericServiceImpl<Option, Long>
        implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private OptionHelper optionHelper;

    @Autowired
    public OptionServiceImpl(@Qualifier("optionDaoImpl") GenericDao<Option, Long> genericDao){
        super(genericDao);
        this.optionDao = (OptionDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOption(String name,
                          String price,
                          String connectionCost,
                          List<Long> inclusiveOptions,
                          List<Long> exclusiveOptions) {

        if(StringUtils.isEmpty(name)){
            throw new OptionException("Option name can`t be empty");
        }
        Option option = new Option();
        option.setName(name);
        option.setPrice(Integer.parseInt(price));
        option.setConnectionCost(Integer.parseInt(connectionCost));

        List<Option> inOptions = optionDao.getOptions(inclusiveOptions);
        List<Option> exOptions = optionDao.getOptions(exclusiveOptions);

        Set<Option> uniqueInOptions = new HashSet<>();
        uniqueInOptions = getAllDependentOptions(inOptions, uniqueInOptions);
        List<Option> uniqueInOptionList = new ArrayList<>(uniqueInOptions);

        if(!Collections.disjoint(uniqueInOptionList, exOptions)){
            throw new OptionException("There are common elements in inclusive and exclusive options");
        }
        if(!uniqueInOptionList.isEmpty()){
            uniqueInOptionList.forEach(option1 -> option1.getParentInclusive().add(option));
            option.setInclusiveOptions(uniqueInOptionList);
        }
        if(!exOptions.isEmpty()){
            exOptions.forEach(option1 -> option1.getParentExclusive().add(option));
            exOptions.forEach(option1 -> option1.getExclusiveOptions().add(option));
            option.setParentExclusive(exOptions);
            option.setExclusiveOptions(exOptions);
        }
        add(option);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Option> getOptionsByIds(List<Long> ids) {
        return optionDao.getOptions(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Option> getOptionsNotIn(List<Option> options) {
        if(options.isEmpty()){
            return findAll();
        }
        return optionDao.getOptionsNotIn(options);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Option getFullOptionById(Long id) {
        return optionDao.getFullOptionById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOptionToTerminalDevice(Long terminalDeviceId, Long optionId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(terminalDeviceId);
        if(!terminalDevice.getTerminalDeviceLocks().isEmpty()){
            throw new TerminalDeviceException("Terminal device is locked");
        }
        if(terminalDevice.getTariffPlan().isArchival()){
            throw new TariffPlanException("Current tariff plan is archival. Can not add options");
        }
        Option newOption = optionDao.find(optionId);
        List<Option> tdOptions = terminalDevice.getOptions();
        List<Option> optionsToAdd = new ArrayList<>();

        // Find options to add
        optionsToAdd = optionHelper.getAllOptions(newOption, optionsToAdd);

        // Find options to delete
        List<Option> exclusiveOptions = optionHelper.getExclusiveOptions(tdOptions, optionsToAdd);
        if(!exclusiveOptions.isEmpty()){
            List<Option> optionToDelete = new ArrayList<>();
            optionToDelete = optionHelper.getAllOptionsWithInclusiveParents(exclusiveOptions, optionToDelete);
            optionHelper.removeOptionsFromTd(terminalDevice, optionToDelete);
            optionToDelete.forEach(
                    option -> optionDao.update(option)
            );
        }
        int money = terminalDevice.getPersonalAccount().getMoney();
        for(Option option: optionsToAdd){
            money -= option.getConnectionCost();
        }
        terminalDevice.getPersonalAccount().setMoney(money);
        terminalDevice.getOptions().addAll(optionsToAdd);
        List<Option> withoutDuplicates = new ArrayList<>(
                new HashSet<>(terminalDevice.getOptions())
        );
        terminalDevice.setOptions(withoutDuplicates);
        terminalDeviceDao.update(terminalDevice);
        personalAccountDao.update(terminalDevice.getPersonalAccount());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOptionFromTerminalDevice(Long terminalDeviceId, Long optionId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(terminalDeviceId);
        if(!terminalDevice.getTerminalDeviceLocks().isEmpty()){
            throw new TerminalDeviceException("Terminal device is locked");
        }
        if(terminalDevice.getTariffPlan().isArchival()){
            throw new TariffPlanException("Current tariff plan is archival. Can not add options");
        }
        Option optionToDelete = optionDao.find(optionId);

        if(optionHelper.existInclusiveConflicts(terminalDevice.getOptions(), optionToDelete)){
            throw new IllegalArgumentException("Cannot delete option. It is required option");
        }
        optionHelper.removeOptionFromTd(terminalDevice, optionToDelete);
        terminalDeviceDao.update(terminalDevice);
        optionDao.update(optionToDelete);
    }

    private Set<Option> getAllDependentOptions(List<Option> options, Set<Option> uniqueOptions){
        for(Option option: options){
            for(Option inOption: option.getInclusiveOptions()){
                uniqueOptions.add(inOption);
                return getAllDependentOptions(inOption.getInclusiveOptions(), uniqueOptions);
            }
        }
        return uniqueOptions;
    }
}
