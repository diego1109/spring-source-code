package com.aop;

import com.aop.config.AppConfig;
import com.aop.domain.TestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

//		User user = context.getBean(User.class);
//
//		System.out.println("name: " + user.getName());
//		System.out.println("age:" + user.getAge());
		TestBean bean = context.getBean(TestBean.class);
		bean.test();
	}
}
