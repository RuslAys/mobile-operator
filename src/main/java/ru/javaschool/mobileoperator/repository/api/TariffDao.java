package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.TariffPlan;

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
    TariffPlan getTariffOnContractByNumber(Long number);

    /**
     * Method to get tariff with options by id
     * @param id tariff plan id
     * @return tariff plan with options
     */
    TariffPlan getTariffWithOptions(Long id);

    /**
     * Method to get all tariffs with relation options
     * @return tariff plan with options
     */
    List<TariffPlan> getTariffsWithOptions();

    /**
     * Method to get tariffs except specified
     * @param tariffPlanId specified tariff plan id
     * @return list of tariffs
     */
    List<TariffPlan> getTariffNotIn(Long tariffPlanId);

    /**
     * Method to get actual tariffs except specified
     * @param tariffPlanId specified tariff plan id
     * @return list of tariffs
     */
    List<TariffPlan> getActualTariffNotIn(Long tariffPlanId);

    /**
     * Method to get all actual tariffs
     * @return list of tariffs
     */
    List<TariffPlan> findActualTariffsWithOptions();
}
