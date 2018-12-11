package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;

import java.util.List;

public interface TariffJmsService {
    /**
     * Method to add new tariff plan and send message to mq
     * @param tariffPlanDto dto
     * @param optionIds tariff options
     */
    void addTariff(TariffPlanDto tariffPlanDto, List<Long> optionIds);

    /**
     * Method to remove option from tariff plan and send message to mq
     * @param tariffId tariff id
     * @param optionId option id
     */
    void removeOptionFromTariff(Long tariffId, Long optionId);

    /**
     * Method to add option to tariff plan and send message to mq
     * @param tariffId tariff id
     * @param optionId option id
     */
    void addOptionToTariff(Long tariffId, Long optionId);

    /**
     * Method to remove tariff plan. Make it archival. Send message to mq
     * @param tariffId tariff id
     */
    void removeTariff(Long tariffId);
}
