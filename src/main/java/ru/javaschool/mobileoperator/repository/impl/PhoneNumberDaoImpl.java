package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.repository.api.PhoneNumberDao;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class PhoneNumberDaoImpl extends GenericDaoImpl<PhoneNumber, Long>
        implements PhoneNumberDao {
    @Override
    public List<PhoneNumber> getAllEmptyNumbers() {
        return currentSession()
                .createQuery("SELECT pn FROM PhoneNumber pn where pn.terminalDevice IS NULL")
                .getResultList();
    }
}
