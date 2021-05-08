package com.aop.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {

	@Value("diego")
	private String name;
	@Value("100")
	private int age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void initAge() {
		this.age = 200;
	}

}
