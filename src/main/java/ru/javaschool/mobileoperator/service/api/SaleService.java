package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;

public interface SaleService {
    void saleContract(Customer customer, TariffPlan tariffPlan, PhoneNumber phoneNumber);
}
