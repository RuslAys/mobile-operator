package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.repository.api.ContractDao;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.service.api.ContractService;
import ru.javaschool.mobileoperator.service.exceptions.PhoneNumberException;
import ru.javaschool.mobileoperator.utils.OptionHelper;

@Service("contractService")
public class ContractServiceImpl extends GenericServiceImpl<Contract, Long> implements ContractService {

    private final Logger logger = LogManager.getLogger(OptionServiceImpl.class);

    @Autowired
    private ContractDao contractDao;

    @Autowired
    public ContractServiceImpl(@Qualifier("contractDaoImpl") GenericDao<Contract, Long> genericDao){
        super(genericDao);
        this.contractDao = (ContractDao) genericDao;
    }

    public ContractServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public Contract getContractWithOptions(String number) {
        if(StringUtils.isEmpty(number)){
            throw new PhoneNumberException("Number cannot be empty");
        }
        return contractDao.getContractWithOptionsByPhoneNumber(Long.parseLong(number));
    }
}
