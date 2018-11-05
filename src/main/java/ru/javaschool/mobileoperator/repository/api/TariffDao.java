package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface TariffDao extends GenericDao<TariffPlan, Long> {
    /**
     * Method to get all tariffs
     * @return list with tariffs
     */
    List<TariffPlan> getAllTariffs();

    /**
     * Method to get tariff by phone number
     * @param number phone number
     * @return tariff on terminal device by number
     */
    TariffPlan getTariffByNumber(Long number);

    /**
     * Method to get tariff with options by id
     * @param id tariff plan id
     * @return tariff plan with options
     */
    TariffPlan getTariffWithOptions(Long id);

    /**
     * Method to get tariffs except specified
     * @param tariffPlan specified tariff
     * @return list of tariffs
     */
    List<TariffPlan> getTariffNotIn(TariffPlan tariffPlan);
}
