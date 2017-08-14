package com.aserta.business.layer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.aserta.business.entities.UserSignIn;
import com.aserta.business.layer.UserSignInService;
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
		
		// act
		UserSignInService service = new UserSignInService(mockUsersRepository);
		String actual = service.execute(userSignIn);
		
		// assert
		String expected = "Francisco Javier Banos Lemoine";
		assertEquals(expected, actual);
	}
	
	@Test(expected = InvalidEmailOrPasswordException.class)
	public void shouldFailWithInvalidEmail() throws InvalidEmailOrPasswordException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		
		userSignIn.setEmail("no.es@bside.com.mx");
		
		// act
		UserSignInService service = new UserSignInService(mockUsersRepository);
		String actual = service.execute(userSignIn);
		
		// assert
	}
	
	@Test(expected = InvalidEmailOrPasswordException.class)
	public void shouldFailWithInvalidPassword() throws InvalidEmailOrPasswordException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		
		userSignIn.setPassword("Inc0rr3ct");
		
		// act
		UserSignInService service = new UserSignInService(mockUsersRepository);
		String actual = service.execute(userSignIn);
		
		// assert
	}
}
