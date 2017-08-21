package com.aserta.business.interfaces;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.layer.EmailAlreadyRegisteredException;

public interface IUserSignUpService {
	int execute(UserSignUp userSignUp) throws EmailAlreadyRegisteredException, Exception;
}
