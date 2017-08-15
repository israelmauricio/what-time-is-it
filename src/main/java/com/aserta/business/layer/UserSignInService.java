package com.aserta.business.layer;

import com.aserta.business.entities.UserSignIn;
import com.aserta.data.interfaces.IUsersRepository;
import com.aserta.operational.management.ILogger;

public class UserSignInService {
	private IUsersRepository usersRepository;
	private ILogger logger;

	public UserSignInService(IUsersRepository usersRepository, ILogger logger) {
		this.usersRepository = usersRepository;
		this.logger = logger;
	}

	public String execute(UserSignIn userSignIn) throws InvalidEmailOrPasswordException {

		userSignIn.validate();

		try {
			String fullname = usersRepository.verify(userSignIn);

			if (fullname == null) {
				throw new InvalidEmailOrPasswordException();
			}

			return fullname;
		} catch (Exception ex) {
			
			logger.error(ex);
			throw ex;
		
		}
	}

}
