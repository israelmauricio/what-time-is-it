package com.aserta;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aserta.business.interfaces.IUserSignUpService;
import com.aserta.business.layer.UserSignUpService;
import com.aserta.data.interfaces.IUsersRepository;
import com.aserta.data.layer.SqlServerUsersRepository;
import com.aserta.operational.management.DefaultLogger;
import com.aserta.operational.management.ILogger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
@PropertySource("application.properties")
public class WhatTimeIsItApplicationConfiguration {

	@Value("${sqlServer}")
	private String sqlServer;
	
	@Value("${sqlServerPort}")
	private int sqlServerPort;
	
	@Value("${sqlServerDatabase}")
	private String sqlServerDatabase;
	
	@Value("${sqlServerUser}")
	private String sqlServerUser;
	
	@Value("${sqlServerPassword}")
	private String sqlServerPassword;
	
	@Bean
	public IUserSignUpService getUserSignUpService() {
		IUsersRepository usersRepository = getUsersRepository();
		ILogger logger = getLogger();
		
		return new UserSignUpService(usersRepository, logger);
	}
	
	private IUsersRepository getUsersRepository() {
		DataSource sqlServerDataSource = getSqlServerDataSource();
		return new SqlServerUsersRepository(sqlServerDataSource);
	}
	
	private DataSource getSqlServerDataSource() {
		SQLServerDataSource dataSource = new SQLServerDataSource();
		
		dataSource.setServerName(sqlServer);
		dataSource.setPortNumber(sqlServerPort);
		dataSource.setDatabaseName(sqlServerDatabase);
		dataSource.setUser(sqlServerUser);
		dataSource.setPassword(sqlServerPassword);
		dataSource.setApplicationName("Spring Boot What Time Is It");

		return dataSource;
	}
	
	private ILogger getLogger() {
		return new DefaultLogger();
	}
	
}
