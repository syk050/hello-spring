package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public class TimeTraceAop {

    // 타겟팅
    //@Around("execution(* hello.hellospring..*(..))")

    // AOP 대상을 지정하는 @Around 코드에서 자기 자신인 TimeTraceAop를 생성하는
    // SpringConfig의 timeTraceAop()도 처리하게 되어 순환참조 문제가 발생
    @Around("execution(* hello.hellospring.controller..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toLongString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END : " + joinPoint.toLongString() + " " + timeMs + "ms");
        }
    }
}
