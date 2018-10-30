package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long>
        implements CustomerDao {
}
