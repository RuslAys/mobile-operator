package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService extends GenericService<Customer, Long> {
    CustomerDto getCustomerByContract(ContractDto contract);
    List<CustomerDto> getAll();
}
