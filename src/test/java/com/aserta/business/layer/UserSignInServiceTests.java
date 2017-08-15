package com.aserta.business.layer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.aserta.business.entities.UserSignIn;
import com.aserta.business.layer.UserSignInService;
import com.aserta.repository.mocks.MockLogger;
import com.aserta.repository.mocks.MockUsersRepository;

public class UserSignInServiceTests {
	private UserSignIn userSignIn;

	@Before
	public void initializeTest() {
		userSignIn = new UserSignIn();

		userSignIn.setEmail("franl@bside.com.mx");
		userSignIn.setPassword("T0p5ecr3t");
	}

	@Test
	public void shouldSucceed() throws InvalidEmailOrPasswordException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();

		// act
		UserSignInService service = new UserSignInService(mockUsersRepository, mockLogger);
		String actual = service.execute(userSignIn);

		// assert
		String expected = "Francisco Javier Banos Lemoine";
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidEmailOrPasswordException.class)
	public void shouldFailWithInvalidEmail() throws InvalidEmailOrPasswordException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();

		userSignIn.setEmail("no.es@bside.com.mx");

		try {
			// act
			UserSignInService service = new UserSignInService(mockUsersRepository, mockLogger);
			service.execute(userSignIn);
		} catch (Exception ex) {
			// assert
			assertTrue(mockLogger.getErrorInvoked());
			throw ex;
		}
	}

	@Test(expected = InvalidEmailOrPasswordException.class)
	public void shouldFailWithInvalidPassword() throws InvalidEmailOrPasswordException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();

		userSignIn.setPassword("Inc0rr3ct");

		try {
			// act
			UserSignInService service = new UserSignInService(mockUsersRepository, mockLogger);
			service.execute(userSignIn);
		} catch (Exception ex) {
			// assert
			assertTrue(mockLogger.getErrorInvoked());
			throw ex;
		}
	}

	@Test(expected = Exception.class)
	public void shouldLogWhenAnErrorOccurrs() throws Exception {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		MockLogger mockLogger = new MockLogger();

		userSignIn.setEmail("crash@domain.com");

		try {
			// act
			UserSignInService service = new UserSignInService(mockUsersRepository, mockLogger);
			service.execute(userSignIn);
		} catch (Exception ex) {
			// assert
			assertTrue(mockLogger.getErrorInvoked());
			throw ex;
		}
	}
}
