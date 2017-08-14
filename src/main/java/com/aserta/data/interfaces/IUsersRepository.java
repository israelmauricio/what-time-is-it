package com.aserta.data.interfaces;

import com.aserta.business.entities.UserSignUp;

public interface IUsersRepository {
	boolean exists(String email);
	int create(UserSignUp userSignUp);
}
