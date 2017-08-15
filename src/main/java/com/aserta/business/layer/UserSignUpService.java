package com.aserta.business.layer;

import com.aserta.business.entities.UserSignUp;
import com.aserta.data.interfaces.IUsersRepository;

public class UserSignUpService {
	
	private IUsersRepository usersRepository;
	
	public UserSignUpService(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public int execute(UserSignUp userSignUp) throws EmailAlreadyRegisteredException, Exception {

		userSignUp.validate();
		
		boolean exists = usersRepository.exists(userSignUp.getEmail());
		if(exists) {
			throw new EmailAlreadyRegisteredException();
		}
		
		return usersRepository.create(userSignUp);
		
	}

}
