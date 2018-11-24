package ru.javaschool.mobileoperator.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //create the root Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(HibernateConfig.class, WebSecurityConfig.class, AspectConfig.class, ActiveMqConfig.class);
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        //Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//        servletAppContext.register(WebAppConfig.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
//        // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//
//        //register and map the dispatcher servlet
//        //note Dispatcher servlet with constructor arguments
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {HibernateConfig.class, WebSecurityConfig.class, AspectConfig.class/*, ActiveMqConfig.class*/};
    }
}
