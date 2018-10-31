package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;

@Repository
public class TerminalDeviceDaoImpl extends GenericDaoImpl<TerminalDevice, Long> implements TerminalDeviceDao {

    @Override
    public TerminalDevice getFullTerminalDeviceByNumber(Long number) {
        TerminalDevice terminalDevice = (TerminalDevice) currentSession()
                .createQuery("SELECT td FROM TerminalDevice td " +
                        "LEFT JOIN FETCH td.personalAccount LEFT JOIN FETCH td.tariffPlan LEFT JOIN FETCH td.options" +
                        " WHERE td.phoneNumber.number = :number")
                .setParameter("number", number)
                .getSingleResult();
        return terminalDevice;
    }
}
