package com.example.AOPdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpresions {

    @Pointcut("execution(* com.example.AOPdemo.dao.*.*(..))")
    public void forDAOPackage(){}

    @Pointcut("execution(* com.example.AOPdemo.dao.*.get*(..))")
    public void getter(){}
    @Pointcut("execution(* com.example.AOPdemo.dao.*.set*(..))")
    public void setter(){}

    @Pointcut("forDAOPackage() && !( getter() || setter() )")
    public void method3(){}
}
