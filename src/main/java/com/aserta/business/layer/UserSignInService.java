package com.aserta.business.layer;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.aserta.business.entities.UserSignIn;
import com.aserta.data.interfaces.IUsersRepository;

public class UserSignInService {
	private IUsersRepository usersRepository;
	
	public UserSignInService(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public String execute(UserSignIn userSignIn) throws InvalidEmailOrPasswordException {

		userSignIn.validate();

		String fullname = usersRepository.verify(userSignIn);
		
		if(fullname == null) {
			throw new InvalidEmailOrPasswordException();
		}
		
		return fullname;
	}

}
