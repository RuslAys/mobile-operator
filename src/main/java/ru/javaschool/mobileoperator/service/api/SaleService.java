package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;

public interface SaleService {

    /**
     * Method to sale new contract
     * @param customer customer
     * @param tariffPlanId tariff plan id
     * @param phoneNumberId phone number id
     */
    void saleContract(Customer customer, Long tariffPlanId, Long phoneNumberId);

    /**
     * Method to sale terminal device to existing personal account
     * @param personalAccountId personal account id
     * @param tariffPlanId tariff plan id
     * @param phoneNumberId phone number id
     */
    void saleToExistPersonalAccount(long personalAccountId, long tariffPlanId, long phoneNumberId);
}
