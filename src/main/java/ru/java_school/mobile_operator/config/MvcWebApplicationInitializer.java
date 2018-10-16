//package ru.java_school.mobile_operator.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class MvcWebApplicationInitializer
//        extends AbstractAnnotationConfigDispatcherServletInitializer {
//    // Load database and spring securty configuration
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{WebSecurityConfig.class };
//    }
//
//    // Load spring web configuration
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[] {WebAppConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] {"/*"};
//    }
//}
