package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.dto.OptionDto;
import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.exceptions.OptionException;
import ru.javaschool.mobileoperator.utils.DtoConverter;
import ru.javaschool.mobileoperator.utils.OptionHelper;

import java.util.*;

@Service("tariffService")
public class TariffServiceImpl extends GenericServiceImpl<TariffPlan, Long>
        implements TariffService {


    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

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
    public void addTariff(TariffPlanDto tariffPlanDto, List<Long> optionIds) {

        List<Option> tariffOptions = optionDao.getOptions(optionIds);
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
        TariffPlan tariffPlan = DtoConverter.dtoToTariffPlanWithoutLists(tariffPlanDto);
        tariffPlan.setOptions(optionsToAdd);
        tariffDao.add(tariffPlan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TariffPlanDto findTariffWithOptions(Long tariffId) {
        TariffPlan tariffPlan = tariffDao.getTariffWithOptions(tariffId);
        TariffPlanDto dto = DtoConverter.toTariffDtoWithLists(tariffPlan);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOptionFromTariff(Long tariffId, Long optionId) {
        TariffPlan tariffPlan = tariffDao.find(tariffId);
        Option optionToDelete = optionDao.find(optionId);
        List<Option> options = new ArrayList<>();
        options.add(optionToDelete);
        Set<Option> optionsToDelete = optionHelper.getParentInclusiveOptionsOnTariffPlan(tariffPlan, options);
        optionsToDelete.add(optionToDelete);
//        if(optionHelper.existInclusiveConflicts(tariffPlan.getOptions(), optionToDelete)){
//            throw new OptionException("Cannot delete option. It is required option");
//        }

        optionHelper.removeOptionsFromTariff(tariffPlan, new ArrayList<>(optionsToDelete));
        tariffDao.update(tariffPlan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOptionToTariff(Long tariffId, Long optionId) {
        TariffPlan tariffPlan = tariffDao.find(tariffId);
        Option newOption = optionDao.find(optionId);

        List<Option> optionsToAdd = new ArrayList<>();

        optionsToAdd.add(newOption);

        // Find options to add
        Set<Option> uniqueOptionsToAdd = optionHelper.getChildInclusiveOptionsOnTariffPlan(tariffPlan, optionsToAdd);
        uniqueOptionsToAdd.add(newOption);

        // Find options to delete
        Set<Option> uniqueOptionsToDelete = optionHelper.getExclusiveOptionsOnTariffPlan(tariffPlan, new ArrayList<>(uniqueOptionsToAdd));

        if(!uniqueOptionsToDelete.isEmpty()){
            List<Option> optionsToDelete = new ArrayList<>(uniqueOptionsToDelete);
            optionHelper.removeOptionsFromTariff(tariffPlan, optionsToDelete);
        }

//        List<Option> tdOptions = tariffPlan.getOptions();
//        List<Option> optionsToAdd = new ArrayList<>();
//
//        // Find options to add
//        optionsToAdd = optionHelper.getAllOptions(newOption, optionsToAdd);
//
//        // Find options to delete
//        List<Option> exclusiveOptions = optionHelper.getExclusiveOptions(tdOptions, optionsToAdd);
//        if(!exclusiveOptions.isEmpty()){
//            List<Option> optionToDelete = new ArrayList<>();
//            optionToDelete = optionHelper.getAllOptionsWithInclusiveParents(exclusiveOptions, optionToDelete);
//            optionHelper.removeOptionsFromTariff(tariffPlan, optionToDelete);
//            optionToDelete.forEach(
//                    option -> optionDao.update(option)
//            );
//        }
//        tariffPlan.getOptions().addAll(optionsToAdd);
//        List<Option> withoutDuplicates = new ArrayList<>(
//                new HashSet<>(tariffPlan.getOptions())
//        );
        tariffPlan.getOptions().addAll(uniqueOptionsToAdd);
        tariffDao.update(tariffPlan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeTariff(Long tariffId) {
        TariffPlan tariffPlan = tariffDao.find(tariffId);
        tariffPlan.setArchival(true);
        tariffDao.update(tariffPlan);
    }

    @Override
    @Transactional(readOnly = true)
    public TariffPlanDto getTariffPlanOnContractByNumber(String number) {
        return DtoConverter.toTariffDtoWithoutLists(
                tariffDao.getTariffOnContractByNumber(Long.parseLong(number)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffPlanDto> getActualTariffsExcept(TariffPlanDto tariffPlan) {
        List<TariffPlanDto> dtos = new ArrayList<>();
        tariffDao.getActualTariffNotIn(tariffPlan.getId()).forEach(
                tariffPlan1 -> dtos.add(DtoConverter.toTariffDtoWithoutLists(tariffPlan1)));
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffPlanDto> findTariffsWithOptions() {
        List<TariffPlan> tariffPlans = tariffDao.getTariffsWithOptions();
        List<TariffPlanDto> dtos = new ArrayList<>();
        tariffPlans.forEach(
                tariffPlan -> dtos.add(DtoConverter.toTariffDtoWithLists(tariffPlan))
        );
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffPlanDto> findAllActualTariffsWithOptions() {
        List<TariffPlan> tariffPlans = tariffDao.findActualTariffsWithOptions();
        List<TariffPlanDto> dtos = new ArrayList<>();
        tariffPlans.forEach(
                tariffPlan -> dtos.add(DtoConverter.toTariffDtoWithLists(tariffPlan))
        );
        return dtos;
    }
}
