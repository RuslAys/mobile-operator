package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.PhoneNumberService;
import ru.javaschool.mobileoperator.service.SaleService;
import ru.javaschool.mobileoperator.service.TariffService;
import ru.javaschool.mobileoperator.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service("saleContractService")
public class SaleServiceImpl implements SaleService {
    @Autowired
    TariffService tariffService;

    @Autowired
    PhoneNumberService phoneNumberService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String getPageWithTariffsAndNumbers(Model model) {
        List<TariffPlan> tariffPlans = tariffService.getAllTariffs();
        List<PhoneNumber> phoneNumbers = phoneNumberService.getAllEmptyNumbers();
        model.addAttribute("tariffs", tariffPlans);
        model.addAttribute("numbers", phoneNumbers);
        return "sale";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saleContract(String firstName,
                               String lastName,
                               Date birthDate,
                               String city,
                               String street,
                               String house,
                               String email,
                               String password,
                               String confirmPassword,
                               Long tariffId,
                               Long numberId) {
        if(password == null || !password.equals(confirmPassword)){
            throw new IllegalArgumentException("Passwords are not equal");
        }
        TariffPlan tariffPlan = tariffService.find(tariffId);
        PhoneNumber number = phoneNumberService.find(numberId);

        User user = new User();
        user.setEnabled(true);
        user.getAuthorities().add(new Authority(user, UserRoleEnum.USER.name()));
        user.setUsername(number.getNumber().toString());
        user.setPassword(passwordEncoder.encode(password));

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);

        user.setEmail(email);
        user.getAddress().setCity(city);
        user.getAddress().setStreet(street);
        user.getAddress().setHouseNumber(house);

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

        //Add all business data to user
        user.getContracts().add(contract);

        userService.add(user);
        phoneNumberService.update(number);
        return "redirect:/sale";
    }
}
