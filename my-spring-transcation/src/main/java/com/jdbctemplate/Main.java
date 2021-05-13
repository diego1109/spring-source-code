package com.jdbctemplate;

import com.jdbctemplate.config.JdbcConfig;
import com.jdbctemplate.domian.User;
import com.jdbctemplate.domian.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				JdbcConfig.class);

		UserRepository userRepository = applicationContext.getBean(UserRepository.class);

		User user = userRepository.QueryUser("li");
		System.out.println("name:"+ user.getName());
		System.out.println("money:"+ user.getMoney());

		userRepository.updateUserMoney("li",20);

		User user2 = userRepository.QueryUser("li");
		System.out.println("name:"+ user2.getName());
		System.out.println("money:"+ user2.getMoney());
	}
}
