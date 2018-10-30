package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

public interface TariffDao extends GenericDao<TariffPlan, Long> {
    List<TariffPlan> getAllTariffs();
}
