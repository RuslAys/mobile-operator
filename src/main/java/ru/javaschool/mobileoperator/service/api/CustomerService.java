package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;

public interface CustomerService extends GenericService<Customer, Long> {
    Customer getCustomerByContract(Contract contract);
}
