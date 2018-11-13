package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;
import ru.javaschool.mobileoperator.service.api.CustomerService;

@Service("customerService")
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerByContract(Contract contract) {
        return customerDao.getCustomerByContract(contract);
    }
}
