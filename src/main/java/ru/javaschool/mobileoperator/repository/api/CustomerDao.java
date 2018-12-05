package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Customer;

public interface CustomerDao extends GenericDao<Customer, Long> {
    Customer getCustomerWithContracts(Customer customer);
}
