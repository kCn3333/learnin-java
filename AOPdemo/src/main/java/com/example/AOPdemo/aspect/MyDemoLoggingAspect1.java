package com.example.AOPdemo.aspect;

import com.example.AOPdemo.Account;
import com.example.AOPdemo.AoPdemoApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Order(1)
@Component
public class MyDemoLoggingAspect1 {

    // run this code before
    @Before("com.example.AOPdemo.aspect.AopExpresions.method3()")
    public void beforeAddAccountAdvice1(JoinPoint joinPoint){

        Object[] args= joinPoint.getArgs();
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        System.out.println(getClass()+" Executing @Before advice "+methodSignature + " args:" + Arrays.toString(joinPoint.getArgs()));

    }
    @AfterReturning(value = "com.example.AOPdemo.aspect.AopExpresions.method3()", returning = "result")
    public void afterAddAccountAdvice1(JoinPoint joinPoint, String result){
        System.out.println(getClass()+" Executing @AfterReturning advice, \n method: "+joinPoint.getSignature().toShortString() + "\n args:" + Arrays.toString(joinPoint.getArgs())+ "\n result: " +result);

    }

    @Around("execution(* com.example.AOPdemo.AoPdemoApplication.takeSomeTime())")
    public void timeMeter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        System.out.println("start");
        Object result =proceedingJoinPoint.proceed();
        System.out.println("stop");
        long stop=System.currentTimeMillis();
        System.out.println("Time : "+ (stop-start) + " " + result);
    }


}
