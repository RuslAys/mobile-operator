package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;
import ru.javaschool.mobileoperator.repository.api.ContractDao;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;
import ru.javaschool.mobileoperator.service.api.CustomerService;
import ru.javaschool.mobileoperator.utils.DtoConverter;

import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ContractDao contractDao;

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerByContract(ContractDto contract) {
        Contract contractPO = contractDao.find(contract.getId());
        return DtoConverter.toCustomerDtoWithLists(customerDao.getCustomerWithContracts(contractPO.getCustomer()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerDao.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(customer -> customerDtos.add(DtoConverter.toCustomerDtoWithLists(customer)));
        return customerDtos;
    }
}
