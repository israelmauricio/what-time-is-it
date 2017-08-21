package com.aserta.data.interfaces;

import com.aserta.business.entities.UserSignIn;
import com.aserta.business.entities.UserSignUp;

public interface IUsersRepository {
	boolean exists(String email) throws Exception;
	int create(UserSignUp userSignUp);
	String verify(UserSignIn userSignIn);
}
