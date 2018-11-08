package ru.javaschool.mobileoperator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.javaschool.mobileoperator.apects.LoggingAspect;
import ru.javaschool.mobileoperator.utils.RoleHelper;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages = {"ru.javaschool.mobileoperator.controller", "ru.javaschool.mobileoperator.aspects"})
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/pages/").suffix(".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
