package ru.javaschool.mobileoperator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.HibernateConfig;
import ru.javaschool.mobileoperator.config.SpringMvcInitializer;
import ru.javaschool.mobileoperator.config.SpringSecurityInitializer;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.repository.api.CustomerDao;
import ru.javaschool.mobileoperator.repository.api.LockDao;
import ru.javaschool.mobileoperator.repository.api.PhoneNumberDao;
import ru.javaschool.mobileoperator.repository.api.UserDao;
import ru.javaschool.mobileoperator.service.api.LockService;
import ru.javaschool.mobileoperator.service.api.SaleService;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class,
        WebAppConfig.class, WebSecurityConfig.class, HibernateConfig.class, AspectConfig.class})

public class LockServiceTest {
    @Autowired
    private LockService lockService;

    @Mock
    private LockDao lockDao;

    @Test
    public void addLockTest(){
        Lock lock = new Lock();
        lock.setName("test lock");
        lockService.add(lock);
        List<Lock> locks = lockService.findAll();
        Assert.assertTrue(locks.contains(lock));
    }
}
