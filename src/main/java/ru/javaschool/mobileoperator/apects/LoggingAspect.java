package ru.javaschool.mobileoperator.apects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    Logger log = LogManager.getLogger(LoggingAspect.class);

    @Around("execution (* ru.javaschool.mobileoperator.controller..*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String args = Arrays.toString(joinPoint.getArgs());
            log.debug("Method " + className + "." + methodName + "(" + args + ")");
            Object result = joinPoint.proceed();
            log.debug("Method " + className + "." + methodName + "(" + args + ")"+
                    " returns " + result.toString() + " page");
            return result;
        }catch (RuntimeException e){
            log.error(e.getMessage() + " " + Arrays.toString(joinPoint.getArgs()) + " in " +
                     joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    @Around("execution (* ru.javaschool.mobileoperator.service.impl..*.*(..))")
    public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String args = Arrays.toString(joinPoint.getArgs());
            log.debug("Method " + className + "." + methodName + "(" + args + ")");
            Object result = joinPoint.proceed();
            log.debug("Method " + className + "." + methodName + "(" + args + ")"+
                    " returns " + result.toString());
            return result;
        }catch (RuntimeException e){
            log.error(e.getMessage() + " " + Arrays.toString(joinPoint.getArgs()) + " in " +
                    joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    @Around("execution (* ru.javaschool.mobileoperator.repository..*.*(..))")
    public Object logAroundDao(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String args = Arrays.toString(joinPoint.getArgs());
            log.debug("Method " + className + "." + methodName + "(" + args + ")");
            Object result = joinPoint.proceed();
            log.debug("Return " + result.toString());
            return result;
        }catch (RuntimeException e){
            log.error(e.getMessage() + " " + Arrays.toString(joinPoint.getArgs()) + " in " +
                    joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    @Around("execution (* ru.javaschool.mobileoperator.utils..*.*(..))")
    public Object logAroundUtils(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String args = Arrays.toString(joinPoint.getArgs());
            log.debug("Method " + className + "." + methodName + "(" + args + ")");
            Object result = joinPoint.proceed();
            log.debug("Method " + className + "." + methodName + "(" + args + ")"+
                    " returns " + result.toString());
            return result;
        }catch (RuntimeException e){
            log.error(e.getMessage() + " " + Arrays.toString(joinPoint.getArgs()) + " in " +
                    joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

}
