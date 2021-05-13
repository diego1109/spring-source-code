package com.jdbctemplate.infrastructure;

import com.jdbctemplate.domian.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getString("name"), rs.getInt("money"));
	}
}
