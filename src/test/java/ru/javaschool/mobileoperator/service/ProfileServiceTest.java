package ru.javaschool.mobileoperator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javaschool.mobileoperator.BaseTest;
import ru.javaschool.mobileoperator.config.H2Config;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.service.api.ContractService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.service.exceptions.BusinessException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, H2Config.class})
@ComponentScan("ru.javaschool.mobileoperator")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileServiceTest extends BaseTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private TariffService tariffService;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        List<Contract> contracts = contractService.findAll();
        contracts.forEach(contract -> contractService.remove(contract));
        List<TariffPlan> tariffPlans = tariffService.findAll();
        tariffPlans.forEach(tariffPlan -> tariffService.remove(tariffPlan));
    }

    @Test
    public void test1lockContractByAdmin(){
        createAdmin();

        Contract contract = new Contract();
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();

        UserDetails userDetails = userService.loadUserByUsername(ADMIN_USERNAME);
        profileService.lockContract(contracts.get(0).getId(), userDetails);

        Contract result = contractService.find(contracts.get(0).getId());
        assertTrue(result.isLocked());
        assertFalse(result.isLockedByUser());
    }

    @Test
    public void test2lockContractByUser(){
        createUser();

        Contract contract = new Contract();
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();

        UserDetails userDetails = userService.loadUserByUsername(USER_USERNAME);
        profileService.lockContract(contracts.get(0).getId(), userDetails);

        Contract result = contractService.find(contracts.get(0).getId());
        assertTrue(result.isLocked());
        assertTrue(result.isLockedByUser());
    }

    @Test(expected = BusinessException.class)
    public void test3unlockContractByUser(){
        Contract contract = new Contract();
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();
        contracts.get(0).setLocked(true);
        contracts.get(0).setLockedByUser(false);
        contractService.update(contracts.get(0));

        UserDetails userDetails = userService.loadUserByUsername(USER_USERNAME);
        profileService.lockContract(contracts.get(0).getId(), userDetails);
    }

    @Test
    public void test4unlockContractByAdmin(){
        Contract contract = new Contract();
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();
        contracts.get(0).setLocked(true);
        contracts.get(0).setLockedByUser(false);
        contractService.update(contracts.get(0));

        UserDetails userDetails = userService.loadUserByUsername(ADMIN_USERNAME);
        profileService.lockContract(contracts.get(0).getId(), userDetails);

        Contract result = contractService.find(contracts.get(0).getId());
        assertFalse(result.isLocked());
        assertFalse(result.isLockedByUser());
    }

    @Test
    public void test5ChangeTariffPlan(){
        Contract contract = new Contract();
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName("first");
        tariffPlan.setPrice(10);
        TariffPlan tariffPlanToChange = new TariffPlan();
        tariffPlanToChange.setName("second");
        tariffPlanToChange.setPrice(11);
        tariffService.add(tariffPlan);
        tariffService.add(tariffPlanToChange);

        List<TariffPlan> tariffPlans = tariffService.findAll();

        contract.setTariffPlan(tariffPlans.get(0));
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();

        profileService.changeTariff(contracts.get(0).getId(), tariffPlans.get(1).getId());

        Contract result = contractService.find(contracts.get(0).getId());
        assertEquals(tariffPlans.get(1), result.getTariffPlan());
    }

    @Test(expected = TariffPlanException.class)
    public void test6ChangeToArchivalTariffPlan(){
        Contract contract = new Contract();
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName("first");
        tariffPlan.setPrice(10);
        TariffPlan tariffPlanToChange = new TariffPlan();
        tariffPlanToChange.setName("second");
        tariffPlanToChange.setPrice(11);
        tariffPlanToChange.setArchival(true);
        tariffService.add(tariffPlan);
        tariffService.add(tariffPlanToChange);

        List<TariffPlan> tariffPlans = tariffService.findAll();

        TariffPlan tariffPlanToAdd = new TariffPlan();
        TariffPlan tariffPlanToChangeTest = new TariffPlan();
        for (TariffPlan tariff : tariffPlans) {
            if(!tariff.isArchival()){
                tariffPlanToAdd = tariff;
            }else {
                tariffPlanToChangeTest = tariff;
            }
        }
        contract.setTariffPlan(tariffPlanToAdd);
        contractService.add(contract);
        List<Contract> contracts = contractService.findAll();

        profileService.changeTariff(contracts.get(0).getId(), tariffPlanToChangeTest.getId());
    }
}