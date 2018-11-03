package ru.javaschool.mobileoperator.apects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    Logger log = LogManager.getLogger(this.getClass().getName());

//    @Pointcut("within(org.springframework.stereotype.Controller *)")
//    public void controller(){
//    }
//
//    @Pointcut("execution(* *.*(..))")
//    public void allMethods(){
//    }
//
//    @Pointcut("execution(public * *.*(..))")
//    public void allPublicMethods(){
//    }

//    @Before("controller() && allMethods() && args(..,request)")
//    @Before("execution(* ru.javaschool.mobileoperator.controller.*.*(..))")
//    public void logBefore(JoinPoint joinPoint, HttpServletRequest request){
//        System.out.println("Aspect is working");
//        log.debug("Entering im method: " + joinPoint.getSignature().getDeclaringTypeName());
//        log.debug("Class name: " + joinPoint.getSignature().getName());
//        log.debug("Arguments: " + Arrays.toString(joinPoint.getArgs()));
//        log.debug("Target class: " + joinPoint.getTarget().getClass().getName());
//        if(null != request){
//            log.debug("Start Header Section of request");
//            log.debug("Method type" + request.getMethod());
//            Enumeration headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()){
//                String headerName = (String) headerNames.nextElement();
//                String headerValue = request.getHeader(headerName);
//                log.debug("Header Name: " + headerName + " Header Value: " + headerValue);
//            }
//            log.debug("Request Path info: " + request.getServletPath());
//            log.debug("End Header Section request");
//        }
//    }

    @Around("execution(* ru.javaschool.mobileoperator.controller.*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.debug("Method " + className + "." + methodName + "()" + " execution time: "
                     + elapsedTime + " ms");
            return result;
        }catch (IllegalArgumentException e){
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in " +
                     joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}
