package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;
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

    /**
     * Method to lock / unlock contract
     * @param contractId contract id
     * @param userDetails principal
     */
    void lockContract(Long contractId, UserDetails userDetails);
}
