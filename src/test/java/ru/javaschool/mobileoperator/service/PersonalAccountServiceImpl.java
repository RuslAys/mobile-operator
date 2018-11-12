package ru.javaschool.mobileoperator.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.H2Config;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;
import ru.javaschool.mobileoperator.domain.PersonalAccount;
import ru.javaschool.mobileoperator.service.api.PersonalAccountService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, H2Config.class, AspectConfig.class})
public class PersonalAccountServiceImpl {
    @Autowired
    PersonalAccountService personalAccountService;

    @Test
    public void findPersonalAccountWithTerminalDevicesTest(){
        PersonalAccount personalAccount = new PersonalAccount();
        personalAccountService.add(personalAccount);
        List<PersonalAccount> all = personalAccountService.findAll();
        PersonalAccount result = personalAccountService.getPersonalAccountWithTerminalDevices(all.get(0).getId());
        Assert.assertNotNull(result.getTerminalDevices());
    }
}
