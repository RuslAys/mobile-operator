package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.api.TariffDao;

import java.util.List;

@Repository
public class TariffDaoImpl extends
        GenericDaoImpl<TariffPlan, Long> implements TariffDao {
    @Override
    public List<TariffPlan> getAllTariffs() {
        return findAll();
    }

    @Override
    public TariffPlan getTariffByNumber(Long number) {
        return (TariffPlan) currentSession()
                .createQuery("SELECT td.tariffPlan FROM TerminalDevice td WHERE " +
                        "td.phoneNumber.number = :number")
                .setParameter("number", number)
                .getSingleResult();
    }
}
