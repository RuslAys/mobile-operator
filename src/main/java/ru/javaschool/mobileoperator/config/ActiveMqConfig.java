package ru.javaschool.mobileoperator.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqConfig {
    private static final String USERNAME = "t";
    private static final String PASSWORD = "t";
    private static final String DEFAULT_DESTINATION = "jms/queue/TariffsMQ";
    private static final String PROVIDED_URL = "http-remoting://127.0.0.1:8080";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
//    @Bean
//    public ActiveMQConnectionFactory amqConnectionFactory(){
//        return new ActiveMQConnectionFactory("tcp://localhost:61616");
//    }
//
//    @Bean
//    public CachingConnectionFactory connectionFactory(){
//        return new CachingConnectionFactory(amqConnectionFactory());
//    }
//
//    @Bean
//    public ActiveMQQueue defaultDestination(){
//        return new ActiveMQQueue("Send2Recv");
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(){
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        jmsTemplate.setDefaultDestination(defaultDestination());
//        return jmsTemplate;
//    }
}