package core.springbasic.common;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {

  @Around("execution(* core.springbasic.web..*.*(..))")
  public void doLog(ProceedingJoinPoint joinPoint) throws Throwable {
    if (UUIDContext.getUUID() == null) {
      UUIDContext.setUUID(UUID.randomUUID().toString());
    }
    log.info("[{}] {}", UUIDContext.getUUID(), joinPoint.getSignature());
    joinPoint.proceed();
  }

  @AfterReturning("execution(* core.springbasic.web..*Controller.*(..))")
  public void logAfter() {
    UUIDContext.clear();
  }
}
