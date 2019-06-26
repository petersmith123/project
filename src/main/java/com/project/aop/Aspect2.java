package com.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class Aspect2 {

    public void around2(ProceedingJoinPoint proceedingJoinPoint){
        String name =proceedingJoinPoint.getSignature().getName();
        Object[] args= proceedingJoinPoint.getArgs();
        Object target=proceedingJoinPoint.getTarget();
        try {
            Object returnObj = proceedingJoinPoint.proceed();
            System.out.println("log--"+returnObj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("args--"+ Arrays.toString(args)+"-name-"+name+"-target-"+target.getClass().getName());

    }

    public  void beforeDo(){
        System.out.println("----------------------XML方式前置通知-----------");
    }
}
