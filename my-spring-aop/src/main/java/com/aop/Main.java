package com.aop;

import com.aop.config.AppConfig;
import com.aop.domain.TestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		TestBean bean = (TestBean) context.getBean("testBeanImpl");
		bean.test(6,3);
	}
}
