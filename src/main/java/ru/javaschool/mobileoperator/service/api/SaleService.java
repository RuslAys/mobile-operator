package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;

public interface SaleService {
    void saleContract(Customer customer, Long tariffPlanId, Long phoneNumberId);

    void saleToExistPersonalAccount(long personalAccountId, long tariffPlanId, long phoneNumberId);
}
