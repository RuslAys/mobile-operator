package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long>
        implements CustomerDao {
    @Override
    public Customer getCustomerByContract(Long contractId) {
        String query = "SELECT c.customer FROM Contract c LEFT JOIN FETCH c.customer.contracts " +
                "LEFT JOIN FETCH c.customer.users WHERE c.id = :contractId";
        return (Customer) currentSession()
                .createQuery(query)
                .setParameter("contractId", contractId)
                .getSingleResult();
    }
}
