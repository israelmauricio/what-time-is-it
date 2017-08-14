package com.aserta.business.layer;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.ValidationException;

import org.junit.Test;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.layer.UserSignUpService;
import com.aserta.repository.mocks.MockUsersRepository;

public class UserSignUpServiceTests {
	
	private static final int MAX_EMAIL_LENGTH = 60;

	@Test
	public void executeShouldSucceed() throws Exception {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		
		UserSignUp userSignUp = new UserSignUp();
		userSignUp.setEmail("franl@bside.com.mx");
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
		
		// act
		UserSignUpService service = new UserSignUpService(mockUsersRepository);
		service.execute(userSignUp);
		
		// assert
	}
	
	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsNull() throws Exception {
		// arrange
		UserSignUp userSignUp = new UserSignUp();
		userSignUp.setEmail(null);
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
		
		// act
		UserSignUpService service = new UserSignUpService(null);
		service.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailIsGreaterThan60Characters() throws Exception {
		// arrange
		UserSignUp userSignUp = new UserSignUp();

		char[] localPart = new char[MAX_EMAIL_LENGTH];
		Arrays.fill(localPart, 'a');
		String email = new String(localPart) + "@domain.com";
		userSignUp.setEmail(email);
		
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
		
		// act
		UserSignUpService service = new UserSignUpService(null);
		service.execute(userSignUp);
		
		// assert		
	}

	@Test(expected = ValidationException.class)
	public void executeShouldFailWhenEmailHasAnInvalidFormat() throws Exception {
		// arrange
		UserSignUp userSignUp = new UserSignUp();
		userSignUp.setEmail("Hello, world!");
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
		
		// act
		UserSignUpService service = new UserSignUpService(null);
		service.execute(userSignUp);
		
		// assert
	}
	
	@Test(expected = EmailAlreadyRegisteredException.class)
	public void executeShouldFailWhenEmailExists() throws EmailAlreadyRegisteredException {
		// arrange
		MockUsersRepository mockUsersRepository = new MockUsersRepository();
		
		UserSignUp userSignUp = new UserSignUp();
		userSignUp.setEmail("no.existe@bside.com.mx");
		userSignUp.setFullname("Francisco Javier Banos Lemoine");
		userSignUp.setPassword("T0p5ecr3t");
		userSignUp.setConfirmPassword("T0p5ecr3t");
		userSignUp.setBirthdate(LocalDate.of(1962, 10, 10));
		
		// act
		UserSignUpService service = new UserSignUpService(mockUsersRepository);
		service.execute(userSignUp);
		
		// assert
	}
}
