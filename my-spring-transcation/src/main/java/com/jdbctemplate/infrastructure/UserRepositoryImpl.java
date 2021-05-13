package com.jdbctemplate.infrastructure;

import com.jdbctemplate.domian.User;
import com.jdbctemplate.domian.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User QueryUser(String name) {
		String querySql = "select name, money from user_money where name = ?";
		return jdbcTemplate.queryForObject(querySql, new Object[]{name}, new UserRowMapper());
	}

	@Override
	public int updateUserMoney(String name, int money) {
		String updateSql = "update user_money set money = ? where name = ?";
		return jdbcTemplate.update(updateSql, money, name);
	}
}
