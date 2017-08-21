package com.aserta.repository.mocks;

import com.aserta.business.entities.UserSignIn;
import com.aserta.business.entities.UserSignUp;
import com.aserta.data.interfaces.IUsersRepository;

public class MockUsersRepository implements IUsersRepository {

	@Override
	public boolean exists(String email) throws Exception {
		
		if(email.equals("crash@domain.com")) {
			throw new Exception();
		}
		
		if (email.equals("no.existe@bside.com.mx")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int create(UserSignUp userSignUp) {
		return 1;
	}

	@Override
	public String verify(UserSignIn userSignIn) {
		boolean valid = userSignIn.getEmail().equals("franl@bside.com.mx") &&
				        userSignIn.getPassword().equals("T0p5ecr3t");
		if (valid) {
			return "Francisco Javier Banos Lemoine";
		} else {
			return null;
		}
	}

}
