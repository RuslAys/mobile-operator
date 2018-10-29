package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.repository.CustomerDao;
import ru.javaschool.mobileoperator.service.PhoneNumberService;
import ru.javaschool.mobileoperator.service.SaleService;
import ru.javaschool.mobileoperator.service.TariffService;
import ru.javaschool.mobileoperator.service.UserService;

import java.util.Date;
import java.util.Set;

@Service("saleContractService")
public class SaleServiceImpl implements SaleService {
    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerDao customerDao;

    /**
     * Method to create new customer
     * @param firstName Customer`s name
     * @param lastName Customer`s surname
     * @param birthDate Customer`s birth date
     * @param city Customer`s city
     * @param street Customer`s street
     * @param house Customer`s house
     * @param email Customer`s email
     * @param passport Customer`s passport
     * @param password Password for account
     * @param confirmPassword Password to confirm password`s correction
     * @param tariffId Id of chosen tariff plan
     * @param numberId Id of chosen phone number
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saleContract(String firstName,
                               String lastName,
                               Date birthDate,
                               String city,
                               String street,
                               String house,
                               String email,
                               String passport,
                               String password,
                               String confirmPassword,
                               Long tariffId,
                               Long numberId) {
        if(password == null || !password.equals(confirmPassword)){
            throw new IllegalArgumentException("Passwords are not equal");
        }
        TariffPlan tariffPlan = tariffService.find(tariffId);
        PhoneNumber number = phoneNumberService.find(numberId);
        if(number.getTerminalDevice() != null){
            throw new IllegalArgumentException("Number already in use");
        }

        // Create user account
        User user = new User(number.getNumber().toString(),
                            passwordEncoder.encode(password),
                            true);
        user.getAuthorities().add(new Authority(user, UserRoleEnum.USER.name()));

        // Create customer
        Customer customer = new Customer(firstName, lastName, birthDate);
        customer.setEmail(email);
        customer.setPassport(passport);
        customer.getAddress().setCity(city);
        customer.getAddress().setStreet(street);
        customer.getAddress().setHouseNumber(house);
        customer.getUsers().add(user);
        user.setCustomer(customer);

        //Create contract and personal account
        Contract contract = new Contract();
        PersonalAccount personalAccount = new PersonalAccount();
        contract.getPersonalAccounts().add(personalAccount);
        personalAccount.setContract(contract);

        //Create terminal device and set it to personal account
        TerminalDevice terminalDevice = new TerminalDevice();
        personalAccount.getTerminalDevices().add(terminalDevice);
        terminalDevice.setPersonalAccount(personalAccount);

        //Fill terminal device by options and tariff
        terminalDevice.setTariffPlan(tariffPlan);
        Set<Option> options = tariffPlan.getOptions();
        terminalDevice.getOptions().addAll(options);

        //Create references to number and terminal device
        terminalDevice.setPhoneNumber(number);
        number.setTerminalDevice(terminalDevice);

        //Add all business data to customer
        customer.getContracts().add(contract);

        customerDao.add(customer);
        userService.add(user);
        phoneNumberService.update(number);
    }
}
