package ru.javaschool.mobileoperator.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javaschool.mobileoperator.BaseTest;
import ru.javaschool.mobileoperator.config.JmsConfig;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.service.api.JmsMessageSender;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, JmsConfig.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class JmsMessageSenderTest extends BaseTest {
    @Autowired
    private JmsMessageSender jmsMessageSender;

    @Test
    public void sendMessageTest(){
        jmsMessageSender.send("Text from test");
    }
}
