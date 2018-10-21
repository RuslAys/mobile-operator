package ru.javaschool.mobileoperator.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("ru.javaschool.mobileoperator.repository"),
    @ComponentScan("ru.javaschool.mobileoperator.service")})
public class HibernateConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();

        // Setting JDBC properties
        properties.put(DRIVER, environment.getProperty("mysql.driver"));
        properties.put(URL, environment.getProperty("mysql.jdbcUrl"));
        properties.put(USER, environment.getProperty("mysql.username"));
        properties.put(PASS, environment.getProperty("mysql.password"));

        // Setting hibernate properties
        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put(DIALECT, environment.getProperty("hibernate.dialect"));

        // Setting C3P0 properties
        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT,
                environment.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS,
                environment.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(User.class, Authority.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            @Autowired SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
