package com.daw.cinema.aop;

import com.daw.cinema.entity.Audit;
import com.daw.cinema.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditService auditService;

    @Around("@annotation(com.daw.cinema.annotation.Audit)")
    public Object saveAuditData(ProceedingJoinPoint joinPoint) throws Throwable {
        Audit audit = new Audit();
        audit.setMethodName(joinPoint.getSignature().getName());
        audit.setArgs(Arrays.toString(joinPoint.getArgs()));

        try {
            final Object returnValue = joinPoint.proceed();
            audit.setReturnValue(String.valueOf(returnValue));
            return returnValue;
        } catch (Throwable e) {
            audit.setExceptionName(e.getClass().getSimpleName());
            audit.setExceptionMessage(e.getMessage());
            throw e;
        } finally {
            audit.setTimestamp(LocalDateTime.now());
            auditService.save(audit);
        }
    }
}
