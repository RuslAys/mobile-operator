package ru.javaschool.mobileoperator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class JmsConfig {
    private static final String USERNAME = "t";
    private static final String PASSWORD = "t";
    private static final String DEFAULT_DESTINATION = "jms/queue/TariffsMQ";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
//    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";

    @Bean
    public Context namingContext() throws NamingException {
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, PROVIDER_URL);
        env.put(Context.SECURITY_PRINCIPAL, USERNAME);
        env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        return new InitialContext(env);
    }

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return (ConnectionFactory) namingContext().lookup(CONNECTION_FACTORY);
    }

    @Bean
    public Destination defaultDestination() throws NamingException {
        return (Destination) namingContext().lookup(DEFAULT_DESTINATION);
    }

    @Bean
    public UserCredentialsConnectionFactoryAdapter queueConnectionFactory() throws NamingException {
        UserCredentialsConnectionFactoryAdapter bean = new UserCredentialsConnectionFactoryAdapter();
        bean.setTargetConnectionFactory(connectionFactory());
        bean.setUsername(USERNAME);
        bean.setPassword(PASSWORD);
        return bean;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(queueConnectionFactory());
        jmsTemplate.setDefaultDestination(defaultDestination());
        return jmsTemplate;
    }
}