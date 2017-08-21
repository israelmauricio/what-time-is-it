package com.aserta;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aserta.business.interfaces.IUserSignUpService;
import com.aserta.business.layer.UserSignUpService;
import com.aserta.data.interfaces.IUsersRepository;
import com.aserta.data.layer.OracleUsersRepository;
import com.aserta.operational.management.DefaultLogger;
import com.aserta.operational.management.ILogger;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
//@PropertySource("classpath:/resources/application.properties")
@PropertySource("application.properties")
	public class WhatTimeIsItApplicationConfiguration {
	
	
		@Value("${Server}")
		private String host;
		@Value("${Serverport}")
		private int port;
		@Value("${ServerDatabase}")
		private String db;
		@Value("${ServerUser}")
		private String user;
		@Value("${ServerPass}")
		private String pwd;
		
		
	
		@Bean
		public IUserSignUpService getUserSignUpService() throws SQLException {
			
			IUsersRepository usersRepository = getUserRepository();
			
			return new UserSignUpService(usersRepository, getLogger());
		}

		public IUsersRepository getUserRepository() throws SQLException {
			// TODO Auto-generated method stub
			DataSource oracleDataSource = getOracleDataSource();
			return new  OracleUsersRepository(oracleDataSource);
		}
		
		public DataSource getOracleDataSource() throws SQLException {
			
			
			   OracleDataSource dataSource = new OracleDataSource();
	        dataSource.setUser(user);
	        dataSource.setPassword(pwd);
	        
	        
	        dataSource.setURL("jdbc:oracle:thin:@//"+host+":"+port+"/"+db);
	        
	        //dataSource.setDatabaseName(db);
	        //dataSource.setPortNumber(port);
	        //dataSource.setServerName(host);
	        dataSource.setFastConnectionFailoverEnabled(true);
	        return dataSource;
			
		}

		public ILogger getLogger() {
			// TODO Auto-generated method stub
			return new DefaultLogger();
		}
		
	}
