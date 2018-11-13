package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

/**
 * Service to work with user profile
 */
public interface ProfileService {
    /**
     * Method to change tariff plan on terminal device
     * @param contractId terminal device id
     * @param newTariffId new tariff plan id
     */
    void changeTariff(Long contractId, Long newTariffId);
}
