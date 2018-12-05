package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.dto.CustomerDto;

public interface SaleService {

    /**
     * Method to sale new contract
     * @param customerDto customer dto
     * @param tariffPlanId tariff plan id
     * @param phoneNumberId phone number id
     */
    void saleContract(CustomerDto customerDto, Long tariffPlanId, Long phoneNumberId);

    /**
     * Method to sale terminal device to existing personal account
     * @param personalAccountId personal account id
     * @param tariffPlanId tariff plan id
     * @param phoneNumberId phone number id
     */
    void saleToExistPersonalAccount(long personalAccountId, long tariffPlanId, long phoneNumberId);
}
