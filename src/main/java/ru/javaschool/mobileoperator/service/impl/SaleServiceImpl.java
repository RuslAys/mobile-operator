package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;
import ru.javaschool.mobileoperator.repository.api.PhoneNumberDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.repository.api.UserDao;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.service.exceptions.PhoneNumberException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;

import java.util.List;

@Service("saleContractService")
public class SaleServiceImpl implements SaleService {

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private PhoneNumberDao phoneNumberDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerDao customerDao;

    /**
     * Method to create new customer
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saleContract(Customer customer, Long tariffPlanId, Long phoneNumberId) {
        PhoneNumber phoneNumber = phoneNumberDao.find(phoneNumberId);
        if(phoneNumber.getContract() != null){
            throw new PhoneNumberException("Number already in use");
        }
        TariffPlan tariffPlan = tariffDao.find(tariffPlanId);
        if(tariffPlan.isArchival()){
            throw new TariffPlanException("Tariff plan is archival");
        }

        String password = "p";

        // Create user account
        User user = new User(phoneNumber.getNumber().toString(),
                            passwordEncoder.encode(password),
                            true);
        user.getAuthorities().add(new Authority(user, UserRoleEnum.USER.name()));
        user.setCustomer(customer);

        //Create contract and personal account
        Contract contract = new Contract();
        customer.getContracts().add(contract);
        contract.setCustomer(customer);

        //Fill contract by options and tariff
        contract.setTariffPlan(tariffPlan);
        List<Option> options = tariffPlan.getOptions();
        contract.getOptions().addAll(options);

        //Create references to number and terminal device
        contract.setPhoneNumber(phoneNumber);
        phoneNumber.setContract(contract);

        //Add all business data to customer
        customer.getContracts().add(contract);

        customerDao.add(customer);
        userDao.add(user);
        phoneNumberDao.update(phoneNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saleToExistPersonalAccount(long customerId, long tariffPlanId, long phoneNumberId) {
        PhoneNumber phoneNumber = phoneNumberDao.find(phoneNumberId);
        if(phoneNumber.getContract() != null){
            throw new PhoneNumberException("Number already in use");
        }
        TariffPlan tariffPlan = tariffDao.find(tariffPlanId);
        if(tariffPlan.isArchival()){
            throw new TariffPlanException("Tariff plan is archival");
        }

        Customer customer = customerDao.find(customerId);

        String password = "p";

        // Create user account
        User user = new User(phoneNumber.getNumber().toString(),
                passwordEncoder.encode(password),
                true);
        user.getAuthorities().add(new Authority(user, UserRoleEnum.USER.name()));
        user.setCustomer(customer);

        //Create terminal device and set it to personal account
        Contract contract = new Contract();
        customer.getContracts().add(contract);
        contract.setCustomer(customer);

        //Fill terminal device by options and tariff
        contract.setTariffPlan(tariffPlan);
        List<Option> options = tariffPlan.getOptions();
        contract.getOptions().addAll(options);

        //Create references to number and terminal device
        contract.setPhoneNumber(phoneNumber);
        phoneNumber.setContract(contract);

        customerDao.update(customer);
        userDao.add(user);
    }
}
