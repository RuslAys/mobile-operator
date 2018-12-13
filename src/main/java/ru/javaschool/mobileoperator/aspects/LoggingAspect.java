package ru.javaschool.mobileoperator.aspects;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution (* ru.javaschool.mobileoperator.controller..*.*(..))")
    private void controllers(){};

    @Pointcut("execution (* ru.javaschool.mobileoperator.service..*.*(..))")
    private void services(){};

    @Pointcut("execution (* ru.javaschool.mobileoperator.repository..*.*(..))")
    private void repositories(){};

    @Around("controllers() || services() || repositories()")
    public Object logMvc(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String args = Arrays.toString(joinPoint.getArgs());
            log.debug("Method " + className + "." + methodName + "(" + args + ")");
            Object result = joinPoint.proceed();
            if(result != null){
                log.debug("Method " + className + "." + methodName + "(" + args + ")"+
                        " returns " + result.toString());
                return result;
            }
        }catch (RuntimeException e){
            log.error(e.getMessage() + " " + Arrays.toString(joinPoint.getArgs()) + " in " +
                     joinPoint.getSignature().getName() + "()" + Arrays.toString(e.getStackTrace()));
            throw e;
        }
        return new Object();
    }
}
