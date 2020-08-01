package com.sample.util.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Around("@annotation(com.sample.util.logging.LogExecutionTime)")
    @SuppressWarnings("checkstyle:IllegalThrows")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;

        List<String> params = getParams(joinPoint);

        log.info("{}, params:{}: {}ms", joinPoint.getSignature(), params, executionTime);

        return proceed;
    }

    private List<String> getParams(ProceedingJoinPoint joinPoint) {
        List<String> result = new ArrayList<>();

        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) {
                result.add("null");
            } else {
                result.add(arg.toString());
            }
        }

        return result;
    }
}
