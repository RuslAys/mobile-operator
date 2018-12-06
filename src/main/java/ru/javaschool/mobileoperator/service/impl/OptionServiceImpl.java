package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
import ru.javaschool.mobileoperator.utils.DtoConverter;
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

        //Search all inclusive options
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

        //Search all exclusive options
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
        List<Option> tdOptions = contract.getOptions();
        List<Option> optionsToAdd = new ArrayList<>();

        // Find options to add
        optionsToAdd = optionHelper.getAllOptions(newOption, optionsToAdd);

        // Find options to delete
        List<Option> exclusiveOptions = optionHelper.getExclusiveOptions(tdOptions, optionsToAdd);
        if(!exclusiveOptions.isEmpty()){
            List<Option> optionsToDelete = new ArrayList<>();
            optionsToDelete = optionHelper.getAllOptionsWithInclusiveParents(exclusiveOptions, optionsToDelete);
            // Find required options for current terminal device option which not will be deleted
            // in options to delete
            for (Option option: optionsToDelete){
                for(Option tdOption: contract.getOptions()){
                    if(!optionsToDelete.contains(tdOption) && option.getParentInclusive().contains(tdOption)){
                        throw new OptionException("Can not remove option " + option + " it`s required for " + tdOption);
                    }
                }
            }
            optionHelper.removeOptionsFromContract(contract, optionsToDelete);
            optionsToDelete.forEach(
                    option -> optionDao.update(option)
            );
        }
        int balance = contract.getBalance();
        for(Option option: optionsToAdd){
            balance -= option.getConnectionCost();
        }
        contract.setBalance(balance);
        contract.getOptions().addAll(optionsToAdd);
        List<Option> withoutDuplicates = new ArrayList<>(
                new HashSet<>(contract.getOptions())
        );
        contract.setOptions(withoutDuplicates);
        contractDao.update(contract);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOptionFromContract(Long contractId, Long optionId) {
        Contract contract = contractDao.find(contractId);
        if(!contract.isLocked()){
            throw new ContractException("Contract is locked");
        }
        if(contract.getTariffPlan().isArchival()){
            throw new TariffPlanException("Current tariff plan is archival. Can not add options");
        }
        Option optionToDelete = optionDao.find(optionId);

        if(optionHelper.existInclusiveConflicts(contract.getOptions(), optionToDelete)){
            throw new IllegalArgumentException("Cannot delete option. It is required option");
        }
        optionHelper.removeOptionFromContract(contract, optionToDelete);
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
    public OptionDto getOptionWithoutList(long id) {
        return DtoConverter.toOptionDtoWithoutLists(find(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getOptionsOnContractToDelete(long contractId, List<Long> optionIds) {
        Contract contract = contractDao.find(contractId);
        List<Option> options = optionDao.getOptions(optionIds);
        Set<Option> uniqueOptions = new HashSet<>();
//        uniqueOptions.addAll(options);
        for (Option o: options){
            uniqueOptions.addAll(o.getParentInclusive());
        }
        if(uniqueOptions.isEmpty()){
            return new ArrayList<>();
        }
        for (Option o: uniqueOptions){
            if(!contract.getOptions().contains(o)){
                uniqueOptions.remove(o);
            }
        }
        List<OptionDto> result = new ArrayList<>();
        uniqueOptions.forEach(option -> result.add(DtoConverter.toOptionDtoWithoutLists(option)));
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
