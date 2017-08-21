package com.aserta.operational.management;

public interface ILogger {
	void error(Exception ex);
	void info(String message);
}
