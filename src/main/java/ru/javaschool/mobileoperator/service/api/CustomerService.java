package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;

public interface CustomerService extends GenericService<Customer, Long> {
    CustomerDto getCustomerByContract(Contract contract);
}
