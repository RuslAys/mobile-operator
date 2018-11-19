package ru.javaschool.mobileoperator.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ComponentScan("ru.javaschool.mobileoperator.service")
public class ActiveMqConfig {
    @Bean
    public ActiveMQConnectionFactory amqConnectionFactory(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        return new CachingConnectionFactory(amqConnectionFactory());
    }

    @Bean
    public ActiveMQQueue defaultDestination(){
        return new ActiveMQQueue("Send2Recv");
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestination(defaultDestination());
        return jmsTemplate;
    }
}