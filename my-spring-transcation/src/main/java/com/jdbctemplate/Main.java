package com.jdbctemplate;

import com.jdbctemplate.config.JdbcConfig;
import com.jdbctemplate.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				JdbcConfig.class);

		UserService userService = applicationContext.getBean(UserService.class);
//		userService.testTranscation();
		userService.test();

	}
}
