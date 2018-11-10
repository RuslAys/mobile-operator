package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PersonalAccount;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;
import ru.javaschool.mobileoperator.repository.api.PhoneNumberDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.repository.api.UserDao;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;

import java.util.Date;
import java.util.List;

@Service("saleContractService")
public class SaleServiceImpl implements SaleService {

    private final Logger logger = LogManager.getLogger(SaleServiceImpl.class);

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
        if(phoneNumber.getTerminalDevice() != null){
            throw new IllegalArgumentException("Number already in use");
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
        PersonalAccount personalAccount = new PersonalAccount();
        contract.getPersonalAccounts().add(personalAccount);
        personalAccount.setContract(contract);
        customer.getContracts().add(contract);
        contract.setCustomer(customer);

        //Create terminal device and set it to personal account
        TerminalDevice terminalDevice = new TerminalDevice();
        personalAccount.getTerminalDevices().add(terminalDevice);
        terminalDevice.setPersonalAccount(personalAccount);

        //Fill terminal device by options and tariff
        terminalDevice.setTariffPlan(tariffPlan);
        List<Option> options = tariffPlan.getOptions();
        terminalDevice.getOptions().addAll(options);

        //Create references to number and terminal device
        terminalDevice.setPhoneNumber(phoneNumber);
        phoneNumber.setTerminalDevice(terminalDevice);

        //Add all business data to customer
        customer.getContracts().add(contract);

        customerDao.add(customer);
        userDao.add(user);
        phoneNumberDao.update(phoneNumber);
    }
}
