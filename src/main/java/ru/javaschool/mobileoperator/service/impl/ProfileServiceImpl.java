package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.api.ContractDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.exceptions.ContractException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;
import ru.javaschool.mobileoperator.utils.OptionHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {



    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private RoleHelper roleHelper;

    @Autowired
    private OptionHelper optionHelper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeTariff(Long contractId, Long newTariffId) {
        Contract contract = contractDao.find(contractId);
        if(contract.isLocked()){
            throw new ContractException("Contract is locked");
        }
        TariffPlan tariffPlan = tariffDao.find(newTariffId);
        if(tariffPlan.isArchival()){
            throw new TariffPlanException("Chosen tariff is archival");
        }
        contract.getOptions().removeAll(contract.getOptions());
        contract.setTariffPlan(tariffPlan);
        contract.getOptions().addAll(tariffPlan.getOptions());
        contractDao.update(contract);
    }

}
