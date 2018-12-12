package ru.javaschool.mobileoperator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.javaschool.mobileoperator.aspects.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "ru.javaschool.mobileoperator.aspects")
public class AspectConfig {
//    @Bean
//    public LoggingAspect loggingAspect() {
//        return new LoggingAspect();
//    }
}
