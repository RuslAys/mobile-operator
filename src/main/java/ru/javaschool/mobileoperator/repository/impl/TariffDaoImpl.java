package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.api.TariffDao;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class TariffDaoImpl extends
        GenericDaoImpl<TariffPlan, Long> implements TariffDao {
    @Override
    public List<TariffPlan> getAllTariffs() {
        return findAll();
    }

    @Override
    public TariffPlan getTariffOnContractByNumber(Long number) {
        return (TariffPlan) currentSession()
                .createQuery("SELECT c.tariffPlan FROM Contract c WHERE " +
                        "c.phoneNumber.number = :number")
                .setParameter("number", number)
                .getSingleResult();
    }

    @Override
    public TariffPlan getTariffWithOptions(Long id) {
        return (TariffPlan) currentSession()
                .createQuery("SELECT tp FROM TariffPlan tp LEFT JOIN FETCH tp.options WHERE tp.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<TariffPlan> getTariffsWithOptions() {
        return currentSession()
                .createQuery("SELECT DISTINCT tp FROM TariffPlan tp LEFT JOIN FETCH tp.options")
                .getResultList();
    }

    @Override
    public List<TariffPlan> getTariffNotIn(Long tariffPlanId) {
        return currentSession()
                .createQuery("SELECT tp FROM TariffPlan tp WHERE tp.id != :id")
                .setParameter("id", tariffPlanId)
                .getResultList();
    }

    @Override
    public List<TariffPlan> getActualTariffNotIn(Long tariffPlanId) {
        return currentSession()
                .createQuery("SELECT tp FROM TariffPlan tp WHERE tp.id != :id AND tp.archival is false")
                .setParameter("id", tariffPlanId)
                .getResultList();
    }
}