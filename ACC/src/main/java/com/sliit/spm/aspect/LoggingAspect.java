/**
 * 
 */
package com.sliit.spm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vimukthi Rajapaksha
 * @date Aug 26, 2019
 */
@Aspect
@Configuration
public class LoggingAspect {
	public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.sliit.spm.*.*.*(..))")
	public void loggingBeforeServices(JoinPoint joinPoint) {
		// advice
		logger.info("begin executing {} with parameters {}", joinPoint, joinPoint.getArgs());
	}

	@AfterReturning(value = "execution(* com.sliit.spm.*.*.*(..))", returning = "result")
	public void loggingAfterReturningServices(JoinPoint joinPoint, Object result) {
		// advice
		logger.info("{} returned with value {}", joinPoint, result);
	}

	@After(value = "execution(* com.sliit.spm.*.*.*(..))")
	public void loggingAfterServices(JoinPoint joinPoint) {
		// advice
		logger.info("after execution of {}", joinPoint);
	}
}
