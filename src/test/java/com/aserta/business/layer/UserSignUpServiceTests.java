package com.aserta.business.layer;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.layer.UserSignUpService;
import com.aserta.repository.mocks.MockLogger;
import com.aserta.repository.mocks.MockUsersRepository;

public class UserSignUpServiceTests {
	
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
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();
		
		// act
		UserSignUpService service = new UserSignUpService(mockUsersRepository, mockLogger);
		int actual = service.execute(userSignUp);
		
		// assert
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsNull() throws Exception {
		// arrange
		MockLogger mockLogger = new MockLogger();
		userSignUp.setEmail(null);
		
		// act
		UserSignUpService service = new UserSignUpService(null, mockLogger);
		service.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsGreaterThan60Characters() throws Exception {
		// arrange
		MockLogger mockLogger = new MockLogger();

		char[] localPart = new char[MAX_EMAIL_LENGTH];
		Arrays.fill(localPart, 'a');
		String email = new String(localPart) + "@domain.com";
		userSignUp.setEmail(email);
		
		// act
		UserSignUpService service = new UserSignUpService(null, mockLogger);
		service.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailHasAnInvalidFormat() throws Exception {
		// arrange
		MockLogger mockLogger = new MockLogger();

		userSignUp.setEmail("Hello, world!");
		
		// act
		UserSignUpService service = new UserSignUpService(null, mockLogger);
		service.execute(userSignUp);
		
		// assert
	}
	
	@Test(expected = EmailAlreadyRegisteredException.class)
	public void executeShouldFailWhenEmailExists() throws EmailAlreadyRegisteredException, Exception {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();

		userSignUp.setEmail("no.existe@bside.com.mx");
		
		// act
		UserSignUpService service = new UserSignUpService(mockUsersRepository, mockLogger);
		service.execute(userSignUp);
		
		// assert
	}

}
