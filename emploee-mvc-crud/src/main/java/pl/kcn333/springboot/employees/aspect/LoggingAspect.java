package pl.kcn333.springboot.employees.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* pl.kcn333.springboot.employees.service.*.*(..))")
    public  void forServicePackage(){}

    @Pointcut("execution(* pl.kcn333.springboot.employees.controller.*.*(..))")
    public  void forControllerPackage(){}

    @Pointcut("execution(* pl.kcn333.springboot.employees.dao.*.*(..))")
    public  void forDaoPackage(){}

    @Pointcut("forServicePackage() || forControllerPackage() || forDaoPackage()")
    public  void forAppFlow(){}


    @Before("forAppFlow()")
    public  void before(JoinPoint joinPoint){

        String method=joinPoint.getSignature().toShortString();
        logger.info(">> @Before: "+method);
        Object[] args = joinPoint.getArgs();

            for (Object arg : args) {
                logger.info(">> argument: " + arg);
            }


    }

    @AfterReturning(pointcut="forAppFlow()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        String method=joinPoint.getSignature().toShortString();
        logger.info(">> @AfterReturning: "+method);

        logger.info("result: "+ result);



    }




}
