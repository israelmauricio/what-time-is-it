package com.aserta.business.layer;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.aserta.business.entities.UserSignUp;

public class UserSignUpProcessor {
	
	public void execute(UserSignUp userSignUp) throws Exception {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		if (!validator.validate(userSignUp).isEmpty()) {
			throw new ValidationException();
		}
		
	}

}
