package com.jdbctemplate.domian;

public interface UserRepository {

	User QueryUser(String name);

	int updateUserMoney(String name, int money);
}
