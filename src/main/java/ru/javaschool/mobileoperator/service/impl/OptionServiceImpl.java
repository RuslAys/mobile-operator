package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.dto.OptionDto;
import ru.javaschool.mobileoperator.repository.api.ContractDao;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.exceptions.ContractException;
import ru.javaschool.mobileoperator.service.exceptions.OptionException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;
import ru.javaschool.mobileoperator.utils.BillHelper;
import ru.javaschool.mobileoperator.utils.DtoConverter;
import ru.javaschool.mobileoperator.utils.OptionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service("optionService")
public class OptionServiceImpl extends GenericServiceImpl<Option, Long>
        implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private OptionHelper optionHelper;

    @Autowired
    public OptionServiceImpl(@Qualifier("optionDaoImpl") GenericDao<Option, Long> genericDao){
        super(genericDao);
        this.optionDao = (OptionDao) genericDao;
    }

    public OptionServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOption(String name,
                             String price,
                             String connectionCost,
                             List<Long> inclusiveOptions,
                             List<Long> exclusiveOptions) {

        if(StringUtils.isEmpty(name)){
            throw new OptionException("Option name can`t be empty");
        }
        // Create option
        Option option = new Option();
        option.setName(name);
        option.setPrice(Integer.parseInt(price));
        option.setConnectionCost(Integer.parseInt(connectionCost));

        //find inclusive and exclusive options
        List<Option> inOptions = optionDao.getOptions(inclusiveOptions);
        List<Option> exOptions = optionDao.getOptions(exclusiveOptions);

        //Search all inclusive options and create relations
        Set<Option> inOptionToAdd = new HashSet<>();
        if(!inOptions.isEmpty()){
            inOptions.forEach(option1 -> optionHelper.getInclusiveOptionsToAdd(option1, inOptionToAdd));
            inOptionToAdd.forEach(option1 -> option1.getParentInclusive().add(option));
            option.getInclusiveOptions().addAll(inOptionToAdd);
        }

        //Search conflicts between exclusive and inclusive options
        if(!Collections.disjoint(inOptionToAdd, exOptions)){
            throw new OptionException("There are common elements in inclusive and exclusive options");
        }

        //Create relations between all exclusive options
        if(!exOptions.isEmpty()){
            exOptions.forEach(option1 -> option1.getParentExclusive().add(option));
            exOptions.forEach(option1 -> option1.getExclusiveOptions().add(option));
            option.setParentExclusive(exOptions);
            option.setExclusiveOptions(exOptions);
        }

        //Save and update all
        inOptionToAdd.forEach(option1 -> optionDao.update(option1));
        exOptions.forEach(option1 -> optionDao.update(option1));
        optionDao.add(option);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<OptionDto> getOptionsByIds(List<Long> ids) {
        List<OptionDto> optionDtos = new ArrayList<>();
        optionDao.getOptions(ids).forEach(
                option -> optionDtos.add(DtoConverter.toOptionDtoWithoutLists(option))
        );
        return optionDtos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<OptionDto> getOptionsNotIn(List<OptionDto> options) {
        List<Long> ids = new ArrayList<>();
        options.forEach(option -> ids.add(option.getId()));
        if(options.isEmpty()){
            List<OptionDto> optionDtos = new ArrayList<>();
            findAll().forEach(option -> optionDtos.add(DtoConverter.toOptionDtoWithoutLists(option)));
            return optionDtos;
        }
        List<OptionDto> optionDtos = new ArrayList<>();
        optionDao.getOptionsNotIn(ids).forEach(option -> optionDtos.add(DtoConverter.toOptionDtoWithoutLists(option)));
        return optionDtos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public OptionDto getFullOptionById(Long id) {
        return DtoConverter.toOptionDtoWithLists(optionDao.getFullOptionById(id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOptionToContract(Long contractId, Long optionId) {
        Contract contract = contractDao.find(contractId);
        if(contract.isLocked()){
            throw new ContractException("Contract is locked");
        }
        if(contract.getTariffPlan().isArchival()){
            throw new TariffPlanException("Current tariff plan is archival. Can not add options");
        }
        Option newOption = optionDao.find(optionId);
        if(contract.getOptions().contains(newOption)){
            throw new OptionException("Option " + newOption.getName() + " already added");
        }
        List<Option> optionsToAdd = new ArrayList<>();

        optionsToAdd.add(newOption);

        // Find options to add
        Set<Option> uniqueOptionsToAdd = optionHelper.getChildInclusiveOptionsOnContract(contract, optionsToAdd);
        uniqueOptionsToAdd.add(newOption);

        // Find options to delete
        Set<Option> uniqueOptionsToDelete = optionHelper.getExclusiveOptionsOnContract(contract, new ArrayList<>(uniqueOptionsToAdd));

        if(!uniqueOptionsToDelete.isEmpty()){
            List<Option> optionsToDelete = new ArrayList<>(uniqueOptionsToDelete);
            optionHelper.removeOptionsFromContract(contract, optionsToDelete);
        }

        int balance = contract.getBalance();
        int difference = 0;
        for(Option option: uniqueOptionsToAdd){
            difference += option.getConnectionCost();
        }
        balance -= difference;
        Bill bill = BillHelper.makeBill(contract, balance, -difference, "Add option " + newOption.getName());
        contract.setBalance(balance);
        contract.getOptions().addAll(uniqueOptionsToAdd);
        contract.getBills().add(bill);
        contractDao.update(contract);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOptionFromContract(Long contractId, Long optionId) {
        Contract contract = contractDao.find(contractId);
        if(contract.isLocked()){
            throw new ContractException("Contract is locked");
        }
        Option optionToDelete = optionDao.find(optionId);

        List<Option> options = new ArrayList<>();
        options.add(optionToDelete);
        Set<Option> exclusiveOptions = optionHelper.getParentInclusiveOptionsOnContract(contract, options);
        exclusiveOptions.add(optionToDelete);
//        if(!check.isEmpty()){
//            throw new IllegalArgumentException("Cannot delete option. It is required option");
//        }
        optionHelper.removeOptionsFromContract(contract, new ArrayList<>(exclusiveOptions));
        contractDao.update(contract);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getOptionsOnContractByNumber(String number) {
        List<OptionDto> optionDtos = new ArrayList<>();
        optionDao.getOptionsOnContractByNumber(Long.parseLong(number)).forEach(
                option -> optionDtos.add(DtoConverter.toOptionDtoWithoutLists(option))
        );
        return optionDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getAllOptionsWithoutLists() {
        List<Option> list = optionDao.findAll();
        List<OptionDto> dtos = new ArrayList<>();
        list.forEach(option -> dtos.add(DtoConverter.toOptionDtoWithoutLists(option)));
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDto getOptionWithoutLists(long id) {
        return DtoConverter.toOptionDtoWithoutLists(optionDao.find(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getParentInclusiveOptionsOnContract(long contractId, List<Long> optionIds) {
        Contract contract = contractDao.find(contractId);
        List<Option> options = optionDao.getOptions(optionIds);
        Set<Option> uniqueOptions = optionHelper.getParentInclusiveOptionsOnContract(contract, options);
        List<OptionDto> result = new ArrayList<>();
        uniqueOptions.forEach(option -> result.add(DtoConverter.toOptionDtoWithoutLists(option)));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getExclusiveOptionsOnContract(long contractId, List<Long> optionIds) {
        Contract contract = contractDao.find(contractId);
        List<Option> options = optionDao.getOptions(optionIds);
        Set<Option> uniqueOptions = optionHelper.getExclusiveOptionsOnContract(contract, options);
        List<OptionDto> result = new ArrayList<>();
        uniqueOptions.forEach(option -> result.add(DtoConverter.toOptionDtoWithoutLists(option)));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getChildInclusiveOptionsOnContract(long contractId, List<Long> optionIds) {
        Contract contract = contractDao.find(contractId);
        List<Option> options = optionDao.getOptions(optionIds);
        Set<Option> uniqueOptions = optionHelper.getChildInclusiveOptionsOnContract(contract, options);
        List<OptionDto> result = new ArrayList<>();
        uniqueOptions.forEach(option -> result.add(DtoConverter.toOptionDtoWithoutLists(option)));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDto findOptionWithListsById(Long optionId) {
        Option option;
        try {
            option = optionDao.findOptionWithListsById(optionId);
        }catch (Exception e){
            return null;
        }
        OptionDto result = DtoConverter.toOptionDtoWithLists(option);
        return result;
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
