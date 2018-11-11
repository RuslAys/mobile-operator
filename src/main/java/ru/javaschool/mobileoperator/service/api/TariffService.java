package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

public interface TariffService extends GenericService<TariffPlan, Long> {
    /**
     * Method to add new tariff plan
     * @param name tariff name
     * @param price tariff price
     * @param optionIds tariff options
     */
    void addTariff(String name, String price, List<Long> optionIds);

    /**
     * Method to find tariff plan with options
     * @param tariffId tariff id
     * @return tariff plan
     */
    TariffPlan findTariffWithOptions(Long tariffId);

    /**
     * Method to remove option from tariff plan
     * @param tariffId tariff id
     * @param optionId option id
     */
    void removeOptionFromTariff(Long tariffId, Long optionId);

    /**
     * Method to add option to tariff plan
     * @param tariffId tariff id
     * @param optionId option id
     */
    void addOptionToTariff(Long tariffId, Long optionId);

    /**
     * Method to remove tariff plan. Make it archival
     * @param tariffId tariff id
     */
    void removeTariff(Long tariffId);
}
