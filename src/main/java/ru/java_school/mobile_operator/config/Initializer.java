package ru.java_school.mobile_operator.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        //Регистрация в контексте конфигурациионного класса
        context.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));

        context.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(context));

        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
