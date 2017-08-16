package com.aserta.business.layer;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.interfaces.IUserSignUpService;
import com.aserta.data.interfaces.IUsersRepository;
import com.aserta.operational.management.ILogger;

public class UserSignUpService implements IUserSignUpService {
	
	private IUsersRepository usersRepository;
	private ILogger logger;
	
	public UserSignUpService(IUsersRepository usersRepository, ILogger logger) {
		this.usersRepository = usersRepository;
		this.logger = logger;
	}
	
	public int execute(UserSignUp userSignUp) throws EmailAlreadyRegisteredException, Exception {

		userSignUp.validate();
		
		try {
			boolean exists = usersRepository.exists(userSignUp.getEmail());

			if(exists) {
				throw new EmailAlreadyRegisteredException();
			}
			
			return usersRepository.create(userSignUp);
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
		
	}

}
