package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.dto.PhoneNumberDto;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.PhoneNumberDao;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.exceptions.PhoneNumberException;
import ru.javaschool.mobileoperator.utils.DtoConverter;

import java.util.ArrayList;
import java.util.List;

@Service("phoneNumberService")
public class PhoneNumberServiceImpl extends GenericServiceImpl<PhoneNumber, Long>
        implements PhoneNumberService {

    private final Logger logger = LogManager.getLogger(PhoneNumberServiceImpl.class);

    @Autowired
    private PhoneNumberDao phoneNumberDao;

    public PhoneNumberServiceImpl() {
    }

    @Autowired
    public PhoneNumberServiceImpl(@Qualifier("phoneNumberDaoImpl")GenericDao<PhoneNumber, Long> genericDao) {
        super(genericDao);
        this.phoneNumberDao = (PhoneNumberDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PhoneNumberDto> getAllNumbers() {
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        phoneNumberDao.findAll().forEach(
                phoneNumber -> phoneNumberDtos.add(DtoConverter.toPhoneNumberDto(phoneNumber)));
        return phoneNumberDtos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PhoneNumberDto> getAllEmptyNumbers() {
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        phoneNumberDao.getAllEmptyNumbers().forEach(
                phoneNumber -> phoneNumberDtos.add(DtoConverter.toPhoneNumberDto(phoneNumber)));
        return phoneNumberDtos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNumber(String number) {
        if(StringUtils.isEmpty(number)){
            throw new PhoneNumberException("Number cannot be empty");
        }
        long parseLong = Long.parseLong(number);
        PhoneNumber phoneNumber = new PhoneNumber(parseLong);
        phoneNumberDao.add(phoneNumber);
    }
}
