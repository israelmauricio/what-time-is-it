package com.aserta.operational.management;

public class DefaultLogger implements ILogger {

	@Override
	public void error(Exception ex) {
		System.err.println(ex.getMessage());
	}

	@Override
	public void info(String message) {
		System.out.println(message);
	}
	
}
