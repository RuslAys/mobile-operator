package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.PersonalAccount;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.PersonalAccountDao;
import ru.javaschool.mobileoperator.service.api.PersonalAccountService;

@Service
public class PersonalAccountServiceImpl extends GenericServiceImpl<PersonalAccount, Long> implements PersonalAccountService {
    private final Logger logger = LogManager.getLogger(PersonalAccountServiceImpl.class);

    @Autowired
    private PersonalAccountDao personalAccountDao;

    public PersonalAccountServiceImpl() {
    }

    @Autowired
    public PersonalAccountServiceImpl(@Qualifier("personalAccountDaoImpl") GenericDao<PersonalAccount, Long> genericDao) {
        super(genericDao);
        this.personalAccountDao = (PersonalAccountDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PersonalAccount getPersonalAccountWithTerminalDevices(Long id) {
        return personalAccountDao.getPersonalAccountWithTerminalDevices(id);
    }
}
