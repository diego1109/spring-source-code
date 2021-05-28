package com.jdbctemplate.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.jdbctemplate")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class JdbcConfig {
	
	//  注入 dataSource，表示数据库是谁，相关信息。
	@Bean
	public DataSource mysqlDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		return dataSource;
	}
	// 注入 jdbcTemplate，通过它执行对数据库的 CRUD。
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(mysqlDataSource());
	}

	// 如果要开启事物，还需要注入 dataSourceTransactionManager。
	// 像个大管家一样，从整体上管理事务的处理过程。如：开始、提交、回滚等操作。
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(){
		return new DataSourceTransactionManager(mysqlDataSource());
	}

}
