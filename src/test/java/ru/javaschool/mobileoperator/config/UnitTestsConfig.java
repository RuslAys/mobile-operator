package ru.javaschool.mobileoperator.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.TariffService;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {
        "ru.javaschool.mobileoperator"
})
public class UnitTestsConfig {
//    @Bean
//    TariffService tariffService(){
//        return Mockito.mock(TariffService.class);
//    }
//
//    @Bean
//    TariffDao tariffDao(){
//        return Mockito.mock(TariffDao.class);
//    }
}
