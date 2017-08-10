package com.aserta.business.layer;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.layer.UserSignUpProcessor;

public class UserSignUpProcessorTests {
	
	UserSignUp userSignUp;
	
	@Before
	public void Initialize() {
		userSignUp = new UserSignUp();
		userSignUp.setEmail("franl@bside.com.mx");
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
	}
	
	private static final int MAX_EMAIL_LENGTH = 60;

	@Test
	public void executeShouldSucceed() throws Exception {
		// arrange
		
		// act
		UserSignUpProcessor processor = new UserSignUpProcessor();
		processor.execute(userSignUp);
		
		// assert
	}
	
	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsNull() throws Exception {
		// arrange
		userSignUp.setEmail(null);
		
		// act
		UserSignUpProcessor processor = new UserSignUpProcessor();
		processor.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsGreaterThan60Characters() throws Exception {
		// arrange
		char[] localPart = new char[MAX_EMAIL_LENGTH];
		Arrays.fill(localPart, 'a');
		String email = new String(localPart) + "@domain.com";
		userSignUp.setEmail(email);
		
		// act
		UserSignUpProcessor processor = new UserSignUpProcessor();
		processor.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailHasAnInvalidFormat() throws Exception {
		// arrange
		userSignUp.setEmail("Hello, world!");
		
		// act
		UserSignUpProcessor processor = new UserSignUpProcessor();
		processor.execute(userSignUp);
		
		// assert		
	}
}
