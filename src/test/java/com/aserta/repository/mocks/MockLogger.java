package com.aserta.repository.mocks;

import com.aserta.operational.management.ILogger;

public class MockLogger implements ILogger {
	
	private boolean errorInvoked;

	public boolean isErrorInvoked() {
		return errorInvoked;
	}

	public void setErrorInvoked(boolean errorInvoked) {
		this.errorInvoked = errorInvoked;
	}
	
	public boolean getErrorInvoked() {
		return errorInvoked;
	}

	@Override
	public void error(Exception ex) {
		setErrorInvoked(true);
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
	}

}
