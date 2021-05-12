package com.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJTest {

	@Pointcut("execution(* com.aop.domain..test(..))")
	public void test() {

	}

	@Before("test()")
	public void beforeTest() {
		System.out.println("before ...");
	}

	@After("test()")
	public void afterTest() {
		System.out.println("after ...");
	}

	@Around("test()")
	public Object aroundTest(ProceedingJoinPoint p) {
		System.out.println("around before ...");
		Object object = null;
		try {
			object = p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("around after ....");
		return object;
	}

	@AfterReturning("test()")
	public void afterReturn() {
		System.out.println("after return ...");
	}

	@AfterThrowing("test()")
	public void afterThrow() {
		System.out.println("after throw ...");
	}
}
