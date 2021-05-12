package com.aop.domain;

import org.springframework.stereotype.Component;

@Component
public class TestBeanImpl implements TestBean {

	private String testStr = "testStr";

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public void test(int numberA, int numberB) {
		System.out.println("目标方法执行: "+ numberA/numberB);
	}
}
