package com.iamgroot.library;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ImGrootConfig {

    /**
     * Aspect method to monitor the performance of the method using the annotation {@link com.iamgroot.library.Performance}
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.iamgroot.library.Performance)")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        String method = joinPoint.getSignature().getName();
        String params = Arrays.toString(joinPoint.getArgs());
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Method [" + method + "] gets called with parameters [" + params +"] Execution took [" + duration + "ms]");
        return proceed;
    }
}
