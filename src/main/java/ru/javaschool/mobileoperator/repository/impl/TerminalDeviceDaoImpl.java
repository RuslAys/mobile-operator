package ru.javaschool.mobileoperator.repository.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;

@Repository
public class TerminalDeviceDaoImpl extends GenericDaoImpl<TerminalDevice, Long> implements TerminalDeviceDao {

    @Override
    public TerminalDevice getTerminalDeviceByNumber(Long number) {
        TerminalDevice terminalDevice = (TerminalDevice) currentSession()
                .createQuery("SELECT td FROM TerminalDevice td WHERE td.phoneNumber.number = :number")
                .setParameter("number", number)
                .getSingleResult();
        Hibernate.initialize(terminalDevice.getOptions());
        Hibernate.initialize(terminalDevice.getLocks());
        Hibernate.initialize(terminalDevice.getTariffPlan());
        Hibernate.initialize(terminalDevice.getPersonalAccount());
        return terminalDevice;
    }
}
