package com.jdbctemplate.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.jdbctemplate")
public class JdbcConfig {
	
	//  注入 dataSource
	@Bean
	public DataSource mysqlDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		return dataSource;
	}
	// 注入 jdbcTemplate;
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(mysqlDataSource());
	}

}
