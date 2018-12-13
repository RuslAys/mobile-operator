package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;

import java.util.List;

/**
 * Service to work with customer
 */
public interface CustomerService extends GenericService<Customer, Long> {
    /**
     * MMethod to get customer by contract dto
     * @param contract contract dto
     * @return customer dto
     */
    CustomerDto getCustomerByContract(ContractDto contract);

    /**
     * Method to get all customers
     * @return list with customers dto
     */
    List<CustomerDto> getAll();
}
