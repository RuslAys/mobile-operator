package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.repository.GenericDao;
import ru.javaschool.mobileoperator.repository.PhoneNumberDao;
import ru.javaschool.mobileoperator.service.PhoneNumberService;

import java.util.List;

@Service("phoneNumberService")
public class PhoneNumberServiceImpl extends GenericServiceImpl<PhoneNumber, Long>
        implements PhoneNumberService {

    @Autowired
    PhoneNumberDao phoneNumberDao;

    public PhoneNumberServiceImpl() {
    }

    @Autowired
    public PhoneNumberServiceImpl(@Qualifier("phoneNumberDaoImpl")GenericDao<PhoneNumber, Long> genericDao) {
        super(genericDao);
        this.phoneNumberDao = (PhoneNumberDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PhoneNumber> getAllNumbers() {
        return phoneNumberDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PhoneNumber> getAllEmptyNumbers() {
        return phoneNumberDao.getAllEmptyNumbers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNumber(String number) {
        if(StringUtils.isEmpty(number)){
            throw new IllegalArgumentException("Number cannot be empty");
        }
        long parseLong = Long.parseLong(number);
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(parseLong);
        phoneNumberDao.add(phoneNumber);
    }
}
