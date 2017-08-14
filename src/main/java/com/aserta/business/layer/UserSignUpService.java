package com.aserta.business.layer;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.aserta.business.entities.UserSignUp;
import com.aserta.data.interfaces.IUsersRepository;

public class UserSignUpService {
	
	private IUsersRepository usersRepository;
	
	public UserSignUpService(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public void execute(UserSignUp userSignUp) throws EmailAlreadyRegisteredException {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		if (!validator.validate(userSignUp).isEmpty()) {
			throw new ValidationException();
		}
		
		boolean exists = usersRepository.exists(userSignUp.getEmail());
		if(exists) {
			throw new EmailAlreadyRegisteredException();
		}
		
	}

}
