package com.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJTest {

	@Pointcut("execution(* com.aop.domain.*.*(..))")
	public void test(){

	}

	@Before("test()")
	public void beforeTest(){
		System.out.println("beforeTest");
	}

	@After("test()")
	public void afterTest(){
		System.out.println("afterTest");
	}

	@Around("test()")
	public Object aroundTest(ProceedingJoinPoint p){
		System.out.println("before 1");
		Object object = null;
		try{
			object = p.proceed();
		}catch (Throwable e){
			e.printStackTrace();
		}
		System.out.println("after 1");
		return object;
	}
}
