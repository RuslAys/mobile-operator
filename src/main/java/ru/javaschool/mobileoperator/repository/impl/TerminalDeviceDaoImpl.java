package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;

@Repository
public class TerminalDeviceDaoImpl extends GenericDaoImpl<TerminalDevice, Long> implements TerminalDeviceDao {

    @Override
    public TerminalDevice getFullTerminalDeviceByNumber(Long number) {
        String query = "SELECT td FROM TerminalDevice td LEFT JOIN FETCH td.personalAccount LEFT JOIN FETCH td.terminalDeviceLocks " +
                "LEFT JOIN FETCH td.tariffPlan LEFT JOIN FETCH td.options " +
                "WHERE td.phoneNumber.number = :number";
        return (TerminalDevice) currentSession()
                .createQuery(query)
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    public TerminalDevice getTerminalDeviceWithLocksByNumber(Long number) {
        String query = "SELECT td FROM TerminalDevice td LEFT JOIN FETCH td.terminalDeviceLocks WHERE td.phoneNumber.number = :number";
        return (TerminalDevice) currentSession()
                .createQuery(query)
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    public TerminalDevice getFullTerminalDeviceById(Long id) {
        String query = "SELECT td FROM TerminalDevice td LEFT JOIN FETCH td.personalAccount LEFT JOIN FETCH td.terminalDeviceLocks " +
                "LEFT JOIN FETCH td.tariffPlan LEFT JOIN FETCH td.options " +
                "WHERE td.id = :id";
        return (TerminalDevice) currentSession()
                .createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }
}
