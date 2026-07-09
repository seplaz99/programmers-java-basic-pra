package com.example.sign_up.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.example.sign_up.controller..*(..))")
    public void controllerLog() {}

    @Around("controllerLog()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        String method =  joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        String httpInfo = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            httpInfo = request.getMethod() + " " + request.getRequestURL();
        }

        System.out.println("[요청 시작] " + httpInfo + " -> " + method);
        System.out.println("[파라미터] " + Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long end = System.currentTimeMillis() - start;
            System.out.println("[요청 완료] " + method + " : " + end + "ms");

            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis() - start;
            System.out.println("[요청 실패] " + method + " : " + end + "ms" + " : 예외메시지 " + e.getMessage());

            throw e;
        }
    }
}
