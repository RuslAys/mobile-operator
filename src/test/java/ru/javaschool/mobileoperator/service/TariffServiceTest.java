package ru.javaschool.mobileoperator.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.BaseTest;
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.H2Config;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.TariffService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, H2Config.class})
@ComponentScan("ru.javaschool.mobileoperator")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TariffServiceTest extends BaseTest {
    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private TariffService tariffService;

    @Before
    public void setUp(){

    }

    @After
    @Transactional
    public void tearDown(){
        List<TariffPlan> tariffPlans = tariffDao.findAll();
        tariffPlans.forEach(tariffPlan -> tariffDao.remove(tariffPlan));
    }

    @Test
    @Transactional
    public void test1GetAllTariffPlans(){
        List<TariffPlan> tariffPlans = new ArrayList<>();
        int tariffPlansNumber = 5;
        for (int i = 0; i < tariffPlansNumber; i++){
            TariffPlan tariffPlan = new TariffPlan();
            tariffPlan.setName("Test" + (i + 1));
            tariffPlan.setPrice(30 + i);
            tariffPlans.add(tariffPlan);

            tariffDao.add(tariffPlan);
        }
        List<TariffPlan> tariffPlans1 = tariffService.findAll();
        Assert.assertTrue(tariffPlans1.size() == tariffPlansNumber);
        Assert.assertTrue(tariffPlans1.containsAll(tariffPlans) && tariffPlans.containsAll(tariffPlans1));
    }

    @Test
    @Transactional
    public void test2GetTariffPlan(){
        List<TariffPlan> tariffPlans = new ArrayList<>();
        int tariffPlansNumber = 5;
        for (int i = 0; i < tariffPlansNumber; i++){
            TariffPlan tariffPlan = new TariffPlan();
            tariffPlan.setName("Test" + (i + 1));
            tariffPlan.setPrice(30 + i);
            tariffPlans.add(tariffPlan);

            tariffDao.add(tariffPlan);
        }
        List<TariffPlan> tariffPlans1 = tariffService.findAll();

        int tariffNumber = 4;

        TariffPlan tariffPlan = tariffService.find(tariffPlans1.get(tariffNumber).getId());
        Assert.assertTrue(tariffPlan.getId() == tariffPlans1.get(tariffNumber).getId()
                            && tariffPlan.getName().equals(tariffPlans1.get(tariffNumber).getName()));
    }

    @Test
    @Transactional
    public void test3RemoveTariff(){
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName("first");
        tariffPlan.setPrice(10);
        tariffService.add(tariffPlan);

        List<TariffPlan> tariffPlans = tariffService.findAll();
        tariffService.removeTariff(tariffPlans.get(0).getId());

        List<TariffPlan> result = tariffService.findAll();

        Assert.assertTrue(result.get(0).isArchival());
    }
}