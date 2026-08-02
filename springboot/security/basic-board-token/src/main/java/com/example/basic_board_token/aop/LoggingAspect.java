package com.example.basic_board_token.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.basic_board_token.controller..*(..))")
    public void controllerLog() { }

    @Around("controllerLog()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        String httpInfo = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            httpInfo = request.getMethod() + " " + request.getRequestURI();
        }

        log.info("[요청 시작] {} -> {}", httpInfo, method);
        log.info("[파라미터] {}", Arrays.toString(joinPoint.getArgs()) );

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long end = System.currentTimeMillis() - start;
            log.info("[요청 완료] {} : {} ms", method, end);

            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis() - start;
            log.info("[요청 실패] {} : {} ms, 예외메시지 {}", method, end, e.getMessage());

            throw e;
        }
    }
}
