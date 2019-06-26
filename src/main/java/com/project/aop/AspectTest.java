package com.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component

public class AspectTest {
    @Pointcut("execution(* com.project.service.Impl..*.*(..))")
    private void pointCut(){
    }

   /* @Before("pointCut()")
    public void beforeDo(){
        System.out.println("beforeDo()-----");
    }

    @After("pointCut()")
    public  void afterDo(){
        System.out.println("after do ****");
    }

    @AfterReturning(pointcut="pointCut()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal){
        System.out.println("joinPoint-"+joinPoint);
        System.out.println("AOP AfterReturning Advice:" + returnVal);
    }*/

    @Around("pointCut()")
    public void aroundDo(ProceedingJoinPoint proceedingJoinPoint){
      String name =proceedingJoinPoint.getSignature().getName();
      Object[] args= proceedingJoinPoint.getArgs();
      Object target=proceedingJoinPoint.getTarget();
        try {
            Object returnObj = proceedingJoinPoint.proceed();
            System.out.println("log--"+returnObj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("args--"+Arrays.toString(args)+"-name-"+name+"-target-"+target.getClass().getName());

    }
}
