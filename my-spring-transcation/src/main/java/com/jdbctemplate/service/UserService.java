package com.jdbctemplate.service;

import com.jdbctemplate.domian.User;
import com.jdbctemplate.domian.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	public UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void test() throws Exception {
		try{
			userService.testTranscation();
		}catch (RuntimeException e){
			System.out.println(e.getMessage());
		}
		printUserMoney("diego");
		printUserMoney("amos");
		restore("diego");
		restore("amos");
	}

	@Transactional
	public void testTranscation() throws Exception {
		userRepository.updateUserMoney("diego",900);
		int number = 8;
		if (number == 9){
			throw new RuntimeException("出错啦！！！");
		}
		userRepository.updateUserMoney("amos",1100);
	}



	public void restore(String name){
		userRepository.updateUserMoney(name,1000);
	}

	public void printUserMoney(String name){
		User user = userRepository.QueryUser(name);
		System.out.println(user.getName()+" : " + user.getMoney());
	}

}


