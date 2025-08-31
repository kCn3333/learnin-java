package com.example.AOPdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class MyDemoLoggingAspect2 {


    // run this code before
    @Before("com.example.AOPdemo.aspect.AopExpresions.method3()")
    public void beforeAddAccountAdvice2(){
        System.out.println(getClass()+" Executing @Before advice ");
    }



}
