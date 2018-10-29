package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.repository.PhoneNumberDao;

@Repository
public class PhoneNumberDaoImpl extends GenericDaoImpl<PhoneNumber, Long>
        implements PhoneNumberDao {
}
